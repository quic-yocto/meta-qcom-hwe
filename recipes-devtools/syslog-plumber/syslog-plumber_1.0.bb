SUMMARY = "system logging helper"
DESCRIPTION = "Utility to redirect ALOG style logs into syslog buffers"

LICENSE = "BSD-3-Clause-Clear"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/${LICENSE};md5=7a434440b651f4a472ca93716d01033a"

COMPATIBLE_MACHINE = "(qcom)"

DEPENDS = "logrotate"

SRCPROJECT = "git://git.codelinaro.org/clo/le/le-utils.git;protocol=https"
SRCBRANCH  = "le-utils.qclinux.1.0.r2-rel"
SRCREV     = "80064c0374bc1f7a3238e45ea8da5cb9addae58c"

SRC_URI = "${SRCPROJECT};branch=${SRCBRANCH};destsuffix=le-utils"

S = "${WORKDIR}/le-utils/syslog-plumber"

do_install() {
    install -d ${D}${includedir}
    install -m 0755 ${S}/include/log.h ${D}${includedir}/
}

python () {
    mach_overrides = d.getVar('MACHINEOVERRIDES').split(":")
    if ('qcom-base-bsp' in mach_overrides):
        raise bb.parse.SkipRecipe("syslog-plumber not compatible with qcom-base-bsp")
}
