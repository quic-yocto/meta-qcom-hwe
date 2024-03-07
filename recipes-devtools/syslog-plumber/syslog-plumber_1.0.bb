SUMMARY = "system logging helper"
DESCRIPTION = "Utility to redirect ALOG style logs into syslog buffers"

LICENSE = "BSD-3-Clause-Clear"
LIC_FILES_CHKSUM = "file://${QCOM_COMMON_LICENSE_DIR}${LICENSE};md5=3771d4920bd6cdb8cbdf1e8344489ee0"

DEPENDS = "logrotate"

SRC_URI += "git://git.codelinaro.org/clo/le/le-utils.git;protocol=https;rev=989cee526729c3a622a3c2726f45e86636636fa1;branch=le-utils.qclinux.1.0.r2-rel"

S = "${WORKDIR}/git/syslog-plumber"

do_install() {
    install -d ${D}${includedir}
    install -m 0755 ${S}/include/log.h ${D}${includedir}/
}
