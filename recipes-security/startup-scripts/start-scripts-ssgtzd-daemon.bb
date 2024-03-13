inherit update-rc.d systemd pkgconfig

LICENSE = "Qualcomm-Technologies-Inc.-Proprietary"
LIC_FILES_CHKSUM = "file://${QCOM_COMMON_LICENSE_DIR}/${LICENSE};md5=58d50a3d36f27f1a1e6089308a49b403"

DESCRIPTION = "Start up script for ssgtzd daemon"

SRC_URI += "file://ssgtzd.service"

INITSCRIPT_NAME = "ssgtzd"

do_install:append() {
       install -d ${D}${systemd_unitdir}/system/
       install -m 0644 ${WORKDIR}/ssgtzd.service -D ${D}${systemd_unitdir}/system/ssgtzd.service
}

SYSTEMD_SERVICE:${PN} = "ssgtzd.service"
FILES:${PN} += "${systemd_unitdir}/system/*"
