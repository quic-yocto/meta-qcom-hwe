inherit module

DESCRIPTION = "QCOM BT drivers"
LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/${LICENSE};md5=801f80980d171dd6425610833a22dbe6"


SRC_URI = "git://git.codelinaro.org/clo/le/platform/vendor/qcom-opensource/bt-kernel.git;protocol=https;rev=d5908d4ca5b9f614d7f3a7b00ad99e9313b63b80;branch=bt-performant.qclinux.1.0.r1-rel;destsuffix=bluetooth/bt-kernel \
           file://bt_dlkm \
           file://bt_dlkm.service"

S = "${WORKDIR}/bluetooth/bt-kernel"

RPROVIDES:${PN} += "kernel-module-bt-kernel"

EXTRA_OEMAKE += "MACHINE='${MACHINE}'"
MAKE_TARGETS = "modules"
MODULES_INSTALL_TARGET = "modules_install"
KERNEL_MODULE_AUTOLOAD += "bt_fm_slim"
