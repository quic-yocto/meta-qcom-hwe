inherit module

DESCRIPTION = "QCOM Graphics drivers"
LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/${LICENSE};md5=801f80980d171dd6425610833a22dbe6"

SRCPROJECT = "git://git.codelinaro.org/clo/le/platform/vendor/qcom/opensource/graphics-kernel.git;protocol=https"
SRCBRANCH  = "gfx-kernel.le.0.0.r1-rel"
SRCREV     = "1525167429745381503e617ce848acca5d12a2c7"

SRC_URI = "${SRCPROJECT};branch=${SRCBRANCH};destsuffix=graphics-kernel"

S = "${WORKDIR}/graphics-kernel"

do_install:append() {
     install -m 0644 ${THISDIR}/kgsl.rules -D ${D}${sysconfdir}/udev/rules.d/kgsl.rules
}

FILES:${PN} += "${sysconfdir}/udev/rules.d"
