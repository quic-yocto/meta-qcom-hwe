SUMMARY = "system logging helper"
DESCRIPTION = "Utility to redirect ALOG style logs into syslog buffers"

LICENSE = "BSD-3-Clause-Clear"
LIC_FILES_CHKSUM = "file://include/log.h;beginline=1;endline=2;md5=aabb851d69c3a788a613fd53fa9db243"

PR = "r0"





SRC_URI = "git://git.codelinaro.org/clo/le/le-utils;protocol=https;branch=le-utils.qclinux.1.0.r1-rel;rev=bf7a1a626db3e17c6973513ba48ab2c134e4d701;subdir=syslog-plumber"

S = "${WORKDIR}/git"

do_install() {
    install -d ${D}${includedir}
    install -m 0755 ${S}/include/log.h ${D}${includedir}/
}
