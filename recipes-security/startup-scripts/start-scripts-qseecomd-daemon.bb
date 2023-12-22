HOMEPAGE = "http://support.cdmatech.com"
LICENSE = "Qualcomm-Technologies-Inc.-Proprietary"
LIC_FILES_CHKSUM = "file://${QCOM_COMMON_LICENSE_DIR}/${LICENSE};md5=58d50a3d36f27f1a1e6089308a49b403"

DESCRIPTION = "Start up script for securemsm qseecomd daemon"

SRC_URI += "file://qseecomd.service"
SRC_URI += "file://99-qseecomd.rules"

#PACKAGE_ARCH = "${MACHINE_ARCH}"
PR = "r3"

INITSCRIPT_NAME = "qseecomd"
#INITSCRIPT_PARAMS = "start 28 S ."

inherit update-rc.d systemd pkgconfig

FILES:${PN} += "${systemd_unitdir}/system/"

do_install:append() {

    install -m 0644 ${WORKDIR}/99-qseecomd.rules -D ${D}${sysconfdir}/udev/rules.d/99-qseecomd.rules
    install -m 0644 ${WORKDIR}/qseecomd.service -D ${D}${systemd_unitdir}/system/qseecomd.service
}

SYSTEMD_SERVICE:${PN} = "qseecomd.service"

FILES:${PN} += "${systemd_unitdir}/system/* \
                ${sysconfdir}/udev/rules.d/99-qseecomd.rules"
