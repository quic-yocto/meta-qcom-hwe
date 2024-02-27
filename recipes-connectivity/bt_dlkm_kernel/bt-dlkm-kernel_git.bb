DESCRIPTION = "QCOM BT drivers"
LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/${LICENSE};md5=801f80980d171dd6425610833a22dbe6"

inherit module

SRC_URI += "git://git.codelinaro.org/clo/le/platform/vendor/qcom-opensource/bt-kernel.git;protocol=https;rev=2643148aa11a9444316bd91eae2d0821391485dd;branch=bt-performant.qclinux.1.0.r1-rel \
           file://bt_dlkm \
           file://bt_dlkm.service \
           "

S = "${WORKDIR}/git"

RPROVIDES:${PN} += "kernel-module-bt-kernel"

EXTRA_OEMAKE += "MACHINE='${MACHINE}'"
MAKE_TARGETS = "modules"
MODULES_INSTALL_TARGET = "modules_install"
KERNEL_MODULE_AUTOLOAD += "bt_fm_slim"
