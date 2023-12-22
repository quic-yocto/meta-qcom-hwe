inherit module deploy

DESCRIPTION = "Qualcomm Technologies, Inc. WLAN CLD3.0 low latency driver"
LICENSE = "ISC & GPL-2.0-only"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/ISC;md5=f3b90e78ea0cffb20bf5cca7947a896d \
                    file://${COMMON_LICENSE_DIR}/GPL-2.0-only;md5=801f80980d171dd6425610833a22dbe6"

DEPENDS += "icnss"

SRC_URI = "git://git.codelinaro.org/clo/le/platform/vendor/qcom-opensource/wlan/qcacld-3.0.git;protocol=https;rev=0acb78aa92771e5b3a4d9edc863bf6de703c30e3;branch=wlan-cld3.driver.lnx.2.0.10.r4-rel;subdir=git/qcacld-3.0 \
           git://git.codelinaro.org/clo/le/platform/vendor/qcom-opensource/wlan/qca-wifi-host-cmn.git;protocol=https;rev=6de065f357a9090efd05be83538a8a282ac8392a;branch=wlan-cmn.driver.lnx.2.0.10.r5-rel;subdir=git/qca-wifi-host-cmn \
           git://git.codelinaro.org/clo/le/platform/vendor/qcom-opensource/wlan/fw-api.git;protocol=https;rev=43ce3eded400e86c2dfc565aa7cd07c90c2e4e5b;branch=wlan-api.lnx.1.0.r230-rel;subdir=git/fw-api \
           git://git.codelinaro.org/clo/le/platform/vendor/qcom-opensource/wlan/platform.git;protocol=https;rev=9dae93388ea4738403a4f04dba0dc2a3c2ded2ba;branch=wlan-platform.qclinux.1.0.r2-rel;subdir=git/platform \
           "

S = "${WORKDIR}/git/qcacld-3.0"

RPROVIDES:${PN} += "kernel-module-qcacld-wlan"

EXTRA_OEMAKE += "MACHINE='${MACHINE}'"
EXTRA_OEMAKE += "CONFIG_QCA_CLD_WLAN=m"
EXTRA_OEMAKE += "CONFIG_CNSS_OUT_OF_TREE=y"
EXTRA_OEMAKE += "CONFIG_ICNSS2=m"
EXTRA_OEMAKE += "CONFIG_CNSS_QMI_SVC=m"
EXTRA_OEMAKE += "CONFIG_CNSS_PLAT_IPC_QMI_SVC=m"
EXTRA_OEMAKE += "CONFIG_WLAN_TX_MON_2_0=n"
EXTRA_OEMAKE += "CONFIG_WLAN_DP_LOCAL_PKT_CAPTURE=n"
EXTRA_OEMAKE += "CONFIG_CNSS_GENL=m"
EXTRA_OEMAKE += "CONFIG_LITHIUM=y"
EXTRA_OEMAKE += "CONFIG_CNSS_QCA6750=y"

python __anonymous () {
    d.setVar('KBUILD_EXTRA_SYMBOLS', d.getVar('STAGING_DIR_HOST') + "/usr/include"  + "/icnss/Module.symvers" )
}

MAKE_TARGETS = "all"
MODULES_INSTALL_TARGET = "modules_install"
KERNEL_MODULE_AUTOLOAD += "wlan"
