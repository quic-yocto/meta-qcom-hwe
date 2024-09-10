inherit update-rc.d systemd pkgconfig
LICENSE          = "Qualcomm-Technologies-Inc.-Proprietary"
LIC_FILES_CHKSUM = "file://${WORKDIR}/qwesd.service;beginline=1;endline=3;md5=a28d9c70d64c8bc2eb91130fe3292f39"

DESCRIPTION = "Start up script for qwesd daemon"

SRC_URI = "file://qwesd.service"
SRC_URI += "file://qwes_license_store"
SRC_URI += "file://qwesipc.conf"

INITSCRIPT_NAME = "qwesd"

inherit update-rc.d systemd pkgconfig

FILES:${PN} += "${systemd_unitdir}/system/qwesd.serivce"
FILES:${PN} += "${sysconfdir}/initscripts/*"

do_install:append() {
       install -d ${D}${systemd_unitdir}/system/
       install -d ${D}${sysconfdir}/initscripts
       install -m 0644 ${WORKDIR}/qwesd.service -D ${D}${systemd_unitdir}/system/qwesd.service
       install -d ${D}${sysconfdir}/tmpfiles.d/
       install -m 0644 ${WORKDIR}/qwesipc.conf ${D}${sysconfdir}/tmpfiles.d/qwesipc.conf
       install -m 0755 ${WORKDIR}/qwes_license_store ${D}${sysconfdir}/initscripts
}

SYSTEMD_SERVICE:${PN} = "qwesd.service"
FILES:${PN} += "${systemd_unitdir}/system/*"
