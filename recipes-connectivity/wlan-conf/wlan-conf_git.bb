inherit autotools systemd
DESCRIPTION = "Device specific config"

LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/${LICENSE};md5=550794465ba0ec5312d6919e203a55f9"


SRC_URI = "git://git.codelinaro.org/clo/le/qcom-opensource/mdm-init.git;protocol=https;rev=fe4ec3b9a69b0eaae41cd08016593de473e2c7b4;branch=wlan-os-service.qclinux.1.1.r1-rel;destsuffix=mdm-init"
SRC_URI += "file://wlan_daemon.service"

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
	fi
}

FILES:${PN} += "${sysconfdir}/systemd/system/*"
FILES:${PN} += "${base_libdir}/firmware/wlan/qca_cld/* ${sysconfdir}/init.d/* "

EXTRA_OECONF += "${@oe.utils.conditional('MACHINE','qcm6490', '--enable-qcm6490=yes', '', d)}"
EXTRA_OECONF += "${@oe.utils.conditional('MACHINE','qcs8550', '--enable-qcs8550=yes', '', d)}"

SYSTEMD_AUTO_ENABLE = "enable"
SYSTEMD_SERVICE:${PN}  = "wlan_daemon.service"
