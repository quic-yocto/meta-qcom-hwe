SUMMARY = "system logging helper"
DESCRIPTION = "Utility to redirect ALOG style logs into syslog buffers"

LICENSE = "BSD-3-Clause-Clear"
LIC_FILES_CHKSUM = "file://include/log.h;beginline=1;endline=2;md5=aabb851d69c3a788a613fd53fa9db243"

DEPENDS = "logrotate"

SRC_URI += "git://git.codelinaro.org/clo/le/le-utils.git;protocol=https;rev=26df736e1e803e5552349ee7aae0048503d4df97;branch=le-utils.qclinux.1.0.r2-rel"

S = "${WORKDIR}/git/syslog-plumber"

do_install() {
    install -d ${D}${includedir}
    install -m 0755 ${S}/include/log.h ${D}${includedir}/
}
