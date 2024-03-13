inherit autotools-brokensep pkgconfig update-rc.d

DESCRIPTION = "WLAN Services required for QCACLD"

LICENSE          = "Qualcomm-Technologies-Inc.-Proprietary"
LIC_FILES_CHKSUM = "file://${QCOM_COMMON_LICENSE_DIR}${LICENSE};md5=58d50a3d36f27f1a1e6089308a49b403"

QCACLD = "qcacld-wlan"

DEPENDS += "data ${QCACLD}"

RDEPENDS:${PN} = "data"


PACKAGE_ARCH = "${MACHINE_ARCH}"

SRC_URI = ""

EXTRA_OECONF = "--enable-debug"

INITSCRIPT_NAME = "start_wlan_services"
INITSCRIPT_PARAMS = "start 90 2 3 4 5 . stop 10 0 1 6 ."

do_install() {
        install -d ${D}${sbindir}/mcc
        install -m 755 ${S}/wlan_services ${D}${sbindir}/mcc
        ln -sf /systemrw/wlan/bin/wlan_services ${D}/usr/sbin/wlan_services
}

do_install:append () {

    if ${@bb.utils.contains('DISTRO_FEATURES', 'systemd', 'true', 'false', d)}; then
     install -m 0755 ${WORKDIR}/wlan-proprietary/wlan-services/qcacld/start_wlan_services -D ${D}${sysconfdir}/initscripts/start_wlan_services
     install -d ${D}/etc/systemd/system/
     install -m 0644 ${WORKDIR}/start_wlan_services.service -D ${D}/etc/systemd/system/start_wlan_services.service
     install -d ${D}/etc/systemd/system/multi-user.target.wants/
     ln -sf /etc/systemd/system/start_wlan_services.service \
                                      ${D}/etc/systemd/system/multi-user.target.wants/start_wlan_services.service
     rm -rf ${D}/etc/init.d/start_wlan_services
    fi
}
