inherit module

DESCRIPTION = "QCOM Graphics drivers"
LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/${LICENSE};md5=801f80980d171dd6425610833a22dbe6"

SRC_URI += "git://git.codelinaro.org/clo/le/platform/vendor/qcom/opensource/graphics-kernel.git;protocol=https;rev=a9eb9bfd3d71b7f15457c8d654a1a51a4e5a1df8;branch=gfx-kernel.le.0.0.r1-rel"

SRC_URI:append:qcs8550 = " file://kgsl_aim300_bringup.patch"

S = "${WORKDIR}/git"
