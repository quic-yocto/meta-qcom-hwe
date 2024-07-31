inherit module

DESCRIPTION = "QCOM Touch drivers"
LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/GPL-2.0-only;md5=801f80980d171dd6425610833a22dbe6"

SRCPROJECT = "git://git.codelinaro.org/clo/le/platform/vendor/opensource/touch-drivers.git;protocol=https"
SRCBRANCH  = "touch-kernel.qclinux.1.0.r2-rel"
SRCREV     = "710d994cabab84ac76688932299036896354be49"

SRC_URI = "${SRCPROJECT};branch=${SRCBRANCH};destsuffix=touch/vendor/qcom/opensource/touch-drivers"

S = "${WORKDIR}/touch/vendor/qcom/opensource/touch-drivers"

RPROVIDES:${PN} += "kernel-module-qcom-touchdlkm"
MAKE_TARGETS = "modules"
MODULES_INSTALL_TARGET = "modules_install"
KERNEL_MODULE_AUTOLOAD += "nt36xxx-i2c"
