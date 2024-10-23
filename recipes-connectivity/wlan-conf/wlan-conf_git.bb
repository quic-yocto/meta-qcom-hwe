inherit autotools systemd
DESCRIPTION = "Device specific config"

LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/${LICENSE};md5=550794465ba0ec5312d6919e203a55f9"

SRCPROJECT = "git://git.codelinaro.org/clo/le/qcom-opensource/mdm-init.git;protocol=https"
SRCBRANCH  = "wlan-os-service.qclinux.1.1.r1-rel"
SRCREV     = "86a12ca727d35f795876d19ea7b0bdf8ffdfe106"

SRC_URI = "${SRCPROJECT};branch=${SRCBRANCH};destsuffix=mdm-init \
           file://0001-wlan_qcm6490-Update-default-driver-as-ath11k.patch \
           file://0002-wlan_qcm6490-add-WoWLAN-pattern-to-wpa_supplicant-co.patch \
           file://wlan_daemon.service"

S = "${WORKDIR}/mdm-init"

do_install:append: () {
	if ${@bb.utils.contains('DISTRO_FEATURES', 'systemd', 'true', 'false', d)}; then
		install -d ${D}/etc/initscripts
		cp ${D}/etc/init.d/wlan ${D}/etc/initscripts/wlan
		install -d ${D}/etc/systemd/system/
		install -m 0644 ${WORKDIR}/wlan_daemon.service -D ${D}/etc/systemd/system/wlan_daemon.service
		install -d ${D}/etc/systemd/system/multi-user.target.wants/
		ln -sf /etc/systemd/system/wlan_daemon.service \
			${D}/etc/systemd/system/multi-user.target.wants/wlan_daemon.service
		if echo ${MACHINE} | grep -q -e "qcs6490" -e "qcm6490"; then
			install -d  ${D}/lib/firmware/updates/
			ln -sf /lib/firmware/qcacld/WCN6855/hw2.1 ${D}/lib/firmware/updates/qca6490
		fi
	fi
}

FILES:${PN} += "${sysconfdir}/systemd/system/*"
FILES:${PN} += "${base_libdir}/firmware/wlan/qca_cld/* ${sysconfdir}/init.d/* "
FILES:${PN} += "${base_libdir}/firmware/updates/ "

EXTRA_OECONF:append:qcm6490 = " --enable-qcm6490=yes "
EXTRA_OECONF:append:qcs9100 = " --enable-upstream=yes "

SYSTEMD_AUTO_ENABLE = "enable"
SYSTEMD_SERVICE:${PN}  = "wlan_daemon.service"
