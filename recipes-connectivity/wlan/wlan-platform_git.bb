DESCRIPTION = "QCOM WLAN platform driver"
LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/${LICENSE};md5=801f80980d171dd6425610833a22dbe6"

inherit module

SRC_URI = "git://git.codelinaro.org/clo/le/platform/vendor/qcom-opensource/wlan/platform.git;protocol=https;rev=fe1d44e365c52cc66c4a39f48cd0919de4b72dee;branch=wlan-platform.qclinux.1.0.r2-rel;destsuffix=wlan/platform"

S = "${WORKDIR}/wlan/platform"

RPROVIDES:${PN} += "kernel-module-wlan-platform"

EXTRA_OEMAKE += "MACHINE='${MACHINE}'"

python __anonymous () {
    machine = d.getVar('MACHINE')
    if (machine == 'qcs8550' or machine == 'qcs8650'):
        d.appendVar('EXTRA_OEMAKE', " CONFIG_PINCTRL_MSM=n WLAN_PLATFORM_DRIVER_CNSS=y")
    elif "qcs6490" in machine or "qcm6490" in machine:
        d.appendVar('EXTRA_OEMAKE', " CONFIG_PINCTRL_MSM=n WLAN_PLATFORM_DRIVER_CNSS=y WLAN_PLATFORM_DRIVER_ICNSS=y")
}

MAKE_TARGETS = "modules"
MODULES_INSTALL_TARGET = "modules_install"
INSANE_SKIP_${PN}-dev = "1"
KERNEL_MODULE_AUTOLOAD += "cnss_nl"
