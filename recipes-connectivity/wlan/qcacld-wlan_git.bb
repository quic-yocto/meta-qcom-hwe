inherit module deploy

DESCRIPTION = "Qualcomm Technologies, Inc. WLAN CLD3.0 low latency driver"
LICENSE = "ISC & GPL-2.0-only"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/ISC;md5=f3b90e78ea0cffb20bf5cca7947a896d \
                    file://${COMMON_LICENSE_DIR}/GPL-2.0-only;md5=801f80980d171dd6425610833a22dbe6"

DEPENDS += "wlan-platform"


SRC_URI += "git://git.codelinaro.org/clo/le/platform/vendor/qcom-opensource/wlan/qcacld-3.0.git;protocol=https;rev=c6ee9123e30a4a216a57067c1dcacce5903fbad2;branch=wlan-cld3.driver.lnx.2.0.14.r1-rel;destsuffix=wlan/qcacld-3.0 \
           git://git.codelinaro.org/clo/le/platform/vendor/qcom-opensource/wlan/qca-wifi-host-cmn.git;protocol=https;rev=9ce27cd37ecaf4dd07468fb49cf02890909566ff;branch=wlan-cmn.driver.lnx.2.0.14.r1-rel;destsuffix=wlan/qca-wifi-host-cmn \
           git://git.codelinaro.org/clo/le/platform/vendor/qcom-opensource/wlan/fw-api.git;protocol=https;rev=db667d4f5d40d8ebac1f7a110db94f82e849cace;branch=wlan-api.lnx.1.0.r230-rel;destsuffix=wlan/fw-api \
           git://git.codelinaro.org/clo/le/platform/vendor/qcom-opensource/wlan/platform.git;protocol=https;rev=ac8d6a2ef817dac15df6c408b2513ab4920bb4e3;branch=wlan-platform.qclinux.1.0.r2-rel;destsuffix=wlan/platform \
           file://qcacld-kbuild.patch \
           "

S = "${WORKDIR}/wlan/qcacld-3.0"

RPROVIDES:${PN} += "kernel-module-qcacld-wlan"

EXTRA_OEMAKE += "MACHINE='${MACHINE}'"
EXTRA_OEMAKE += "CONFIG_CNSS_OUT_OF_TREE=y"
EXTRA_OEMAKE += "CONFIG_CNSS_QMI_SVC=m"
EXTRA_OEMAKE += "CONFIG_CNSS_PLAT_IPC_QMI_SVC=m"
EXTRA_OEMAKE += "CONFIG_CNSS_GENL=m"

python __anonymous () {
    d.setVar('KBUILD_EXTRA_SYMBOLS', d.getVar('STAGING_DIR_HOST') + "/usr/include"  + "/wlan-platform/Module.symvers" )
    machine = d.getVar('MACHINE')
    if (machine == 'qcs8550' or machine == 'qcs8650'):
        d.appendVar('EXTRA_OEMAKE', " CONFIG_CNSS_KIWI_V2=y")
        d.appendVar('EXTRA_OEMAKE', " CONFIG_QCA_CLD_WLAN_PROFILE=kiwi_v2")
        d.appendVar('EXTRA_OEMAKE', " CONFIG_CNSS2=m")
        d.appendVar('EXTRA_OEMAKE', " CONFIG_CNSS2_QMI=y")
        d.appendVar('EXTRA_OEMAKE', " CONFIG_CNSS_UTILS=m")
        d.appendVar('EXTRA_OEMAKE', " CONFIG_WCNSS_MEM_PRE_ALLOC=m")
        d.appendVar('EXTRA_OEMAKE', " KERNEL_SUPPORTS_NESTED_COMPOSITES=n")
        d.appendVar('EXTRA_OEMAKE', " WLAN_CTRL_NAME=wlan")
        d.appendVar('EXTRA_OEMAKE', " CONFIG_QCOM_TDLS=n")
    else:
        d.appendVar('EXTRA_OEMAKE', " CONFIG_CNSS_QCA6750=y")
        d.appendVar('EXTRA_OEMAKE', " CONFIG_QCA_CLD_WLAN=m")
        d.appendVar('EXTRA_OEMAKE', " CONFIG_ICNSS2=m")
        d.appendVar('EXTRA_OEMAKE', " CONFIG_LITHIUM=y")
        d.appendVar('EXTRA_OEMAKE', " CONFIG_WLAN_TX_MON_2_0=n")
        d.appendVar('EXTRA_OEMAKE', " CONFIG_WLAN_DP_LOCAL_PKT_CAPTURE=n")
}

do_patch() {
     cd ${S}
     patch -p1 < ${WORKDIR}/qcacld-kbuild.patch
}

MAKE_TARGETS = "all"
MODULES_INSTALL_TARGET = "modules_install"
KERNEL_MODULE_AUTOLOAD += "wlan"
