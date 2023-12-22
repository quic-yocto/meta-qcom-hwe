inherit update-rc.d systemd pkgconfig
HOMEPAGE = "http://support.cdmatech.com"
LICENSE = "Qualcomm-Technologies-Inc.-Proprietary"
LIC_FILES_CHKSUM = "file://${WORKDIR}/ssgtzd.service;beginline=1;endline=3;\
md5=d7e0355d046ceed2bfe04338e27b412b"

DESCRIPTION = "Start up script for ssgtzd daemon"

SRC_URI += "file://ssgtzd.service"

INITSCRIPT_NAME = "ssgtzd"

PR = "r1"

FILES:${PN} += "${systemd_unitdir}/system/ssgtzd.service"
do_install:append() {
       install -d ${D}${systemd_unitdir}/system/
       install -m 0644 ${WORKDIR}/ssgtzd.service -D ${D}${systemd_unitdir}/system/ssgtzd.service
}

SYSTEMD_SERVICE:${PN} = "ssgtzd.service"
FILES:${PN} += "${systemd_unitdir}/system/* \
