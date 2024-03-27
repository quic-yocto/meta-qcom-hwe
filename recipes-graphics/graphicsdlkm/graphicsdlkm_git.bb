inherit module

DESCRIPTION = "QCOM Graphics drivers"
LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/${LICENSE};md5=801f80980d171dd6425610833a22dbe6"

SRC_URI     =  "git://git.codelinaro.org/clo/le/platform/vendor/qcom/opensource/graphics-kernel.git;protocol=https;rev=b56a69763dc4568ce80ed149e4d762f59552b3e9;branch=gfx-kernel.le.0.0.r1-rel;destsuffix=graphics-kernel"
FILESEXTRAPATHS:prepend = "${THISDIR}:"
SRC_URI     += "${@bb.utils.contains('MACHINE', 'qcs8550', 'file://kgsl_aim300_bringup.patch', '', d)}"

S = "${WORKDIR}/graphics-kernel"

do_install:append() {
     install -m 0644 ${THISDIR}/kgsl.rules -D ${D}${sysconfdir}/udev/rules.d/kgsl.rules
}

FILES:${PN} += "${sysconfdir}/udev/rules.d"
