inherit module

DESCRIPTION = "TOSHIBA QPS615 driver"
LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/files/common-licenses/${LICENSE};md5=801f80980d171dd6425610833a22dbe6"

SRC_URI     =  "git://git.codelinaro.org/clo/le/platform/vendor/opensource/data-eth.git;protocol=https;rev=988995f8991c72d114783c178ef4a89b6d16b4dd;branch=data-kernel.qclinux.1.0.r1-rel;destsuffix=data-eth"

S = "${WORKDIR}/data-eth/drivers/qps615/src"
MAKE_TARGETS = "modules"

MODULE_NAME = "tc956x_pcie_eth"
EXTRA_OEMAKE += " MACHINE='${MACHINE}'"
RPROVIDES:${PN} += "kernel-module-qps615"

RRECOMMENDS:${PN} += "qps615-firmware"

KERNEL_MODULE_AUTOLOAD += "${MODULE_NAME}"

RM_WORK_EXCLUDE += "${PN}"