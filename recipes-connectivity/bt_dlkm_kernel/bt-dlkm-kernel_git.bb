inherit module

DESCRIPTION = "QCOM BT drivers"
LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/${LICENSE};md5=801f80980d171dd6425610833a22dbe6"

SRCPROJECT = "git://git.codelinaro.org/clo/le/platform/vendor/qcom-opensource/bt-kernel.git;protocol=https"
SRCBRANCH  = "bt-performant.qclinux.1.0.r1-rel"
SRCREV     = "b6d70a12ab4dbc53f59570f26c90fb7d3eef1719"

SRC_URI = "${SRCPROJECT};branch=${SRCBRANCH};destsuffix=bluetooth/bt-kernel \
           file://bt_dlkm \
           file://bt_dlkm.service"

S = "${WORKDIR}/bluetooth/bt-kernel"

RPROVIDES:${PN} += "kernel-module-bt-kernel"

EXTRA_OEMAKE += "MACHINE='${MACHINE}'"
MAKE_TARGETS = "modules"
MODULES_INSTALL_TARGET = "modules_install"
KERNEL_MODULE_AUTOLOAD += "bt_fm_slim"
