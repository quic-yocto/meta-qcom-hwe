DESCRIPTION = "QCOM WLAN platform driver"
LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/${LICENSE};md5=801f80980d171dd6425610833a22dbe6"

inherit module

SRC_URI = "git://git.codelinaro.org/clo/le/platform/vendor/qcom-opensource/wlan/platform.git;protocol=https;rev=9dae93388ea4738403a4f04dba0dc2a3c2ded2ba;branch=wlan-platform.qclinux.1.0.r2-rel"

S = "${WORKDIR}/git"

RPROVIDES:${PN} += "kernel-module-icnss"

EXTRA_OEMAKE += "MACHINE='${MACHINE}'"
MAKE_TARGETS = "modules"
MODULES_INSTALL_TARGET = "modules_install"
INSANE_SKIP_${PN}-dev = "1"
KERNEL_MODULE_AUTOLOAD += "cnss_nl"
