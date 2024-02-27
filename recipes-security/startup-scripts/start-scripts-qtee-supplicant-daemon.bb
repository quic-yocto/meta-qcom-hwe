LICENSE = "Qualcomm-Technologies-Inc.-Proprietary"
LIC_FILES_CHKSUM = "file://${QCOM_COMMON_LICENSE_DIR}/${LICENSE};md5=58d50a3d36f27f1a1e6089308a49b403"

DESCRIPTION = "Start up script for securemsm qtee_supplicant daemon"

SRC_URI += "file://qteesupplicant.service"
SRC_URI += "file://99-qtee-supplicant.rules"

INITSCRIPT_NAME = "qteesupplicant"
#INITSCRIPT_PARAMS = "start 28 S ."

inherit update-rc.d systemd pkgconfig

FILES:${PN} += "${systemd_unitdir}/system/"

do_install:append() {

    install -m 0644 ${WORKDIR}/99-qtee-supplicant.rules -D ${D}${sysconfdir}/udev/rules.d/99-qtee-supplicant.rules
    install -m 0644 ${WORKDIR}/qteesupplicant.service -D ${D}${systemd_unitdir}/system/qteesupplicant.service
}

SYSTEMD_SERVICE:${PN} = "qteesupplicant.service"

FILES:${PN} += "${systemd_unitdir}/system/* \
                ${sysconfdir}/udev/rules.d/99-qtee-supplicant.rules"
