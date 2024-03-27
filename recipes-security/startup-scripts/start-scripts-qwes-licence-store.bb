LICENSE = "Qualcomm-Technologies-Inc.-Proprietary"
LIC_FILES_CHKSUM = "file://${WORKDIR}/qwes_license_store;beginline=5;endline=7;md5=6f96df626610d7b1d08051483d39a62e"

DESCRIPTION = "Script for creating licensestore and qwes_ipc"

SRC_URI += "file://qwes_license_store"
SRC_URI += "file://qwesipc.conf"

INITSCRIPT_NAME = "qwes-licence-store"

inherit update-rc.d systemd pkgconfig

FILES:${PN} += "${systemd_unitdir}/system/"
FILES:${PN} += "${systemd_unitdir}/system/*"
FILES:${PN} += "${sysconfdir}/initscripts/*"

do_install:append() {
    install -d ${D}${sysconfdir}/initscripts
    install -d ${D}${systemd_unitdir}/system/

    install -d ${D}${sysconfdir}/tmpfiles.d/
    install -m 0644 ${WORKDIR}/qwesipc.conf ${D}${sysconfdir}/tmpfiles.d/qwesipc.conf
    install -m 0755 ${WORKDIR}/qwes_license_store ${D}${sysconfdir}/initscripts
}

SYSTEMD_SERVICE:${PN} = ""
