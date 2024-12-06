inherit module deploy

DESCRIPTION = "Qualcomm Technologies, Inc. WLAN CLD3.0 low latency driver"
LICENSE = "ISC & GPL-2.0-only"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/ISC;md5=f3b90e78ea0cffb20bf5cca7947a896d \
                    file://${COMMON_LICENSE_DIR}/GPL-2.0-only;md5=801f80980d171dd6425610833a22dbe6"

DEPENDS += "wlan-platform"

QCOM_WLAN_QCACLD_SRC ?= "git://git.codelinaro.org/clo/le/platform/vendor/qcom-opensource/wlan/qcacld-3.0.git;protocol=https"
QCOM_WLAN_QCACLD_SRCBRANCH ?= "wlan-cld3.driver.lnx.2.0.14.r1-rel"
QCOM_WLAN_QCACLD_SRCREV ?= "e472004bf8a0880e2313da63fe854503a7e33b05"

QCOM_WLAN_HOST_CMN_SRC ?= "git://git.codelinaro.org/clo/le/platform/vendor/qcom-opensource/wlan/qca-wifi-host-cmn.git;protocol=https"
QCOM_WLAN_HOST_CMN_SRCBRANCH ?= "wlan-cmn.driver.lnx.2.0.14.r1-rel"
QCOM_WLAN_HOST_CMN_SRCREV    ?= "f8aabe051235c4d30fc116dad3e87f1b6b977316"

QCOM_FW_API_SRC ?= "git://git.codelinaro.org/clo/le/platform/vendor/qcom-opensource/wlan/fw-api.git;protocol=https"
QCOM_FW_API_SRCBRANCH ?= "wlan-api.lnx.1.0.r230-rel"
QCOM_FW_API_SRCREV ?= "70669027a35ccc000df35058626d5d6224ac868c"

QCOM_WLAN_SRC ?= "git://git.codelinaro.org/clo/le/platform/vendor/qcom-opensource/wlan/platform.git;protocol=https"
QCOM_WLAN_SRCBRANCH ?= "wlan-platform.qclinux.1.0.r2-rel"
QCOM_WLAN_SRCREV ?= "20ee98faaff14a91a99552778ba692f02ce9b688"

SRC_URI = " \
    ${QCOM_WLAN_QCACLD_SRC};branch=${QCOM_WLAN_QCACLD_SRCBRANCH};rev=${QCOM_WLAN_QCACLD_SRCREV};destsuffix=wlan/qcacld-3.0 \
    ${QCOM_WLAN_HOST_CMN_SRC};branch=${QCOM_WLAN_HOST_CMN_SRCBRANCH};rev=${QCOM_WLAN_HOST_CMN_SRCREV};destsuffix=wlan/qca-wifi-host-cmn \
    ${QCOM_FW_API_SRC};branch=${QCOM_FW_API_SRCBRANCH};rev=${QCOM_FW_API_SRCREV};destsuffix=wlan/fw-api \
    ${QCOM_WLAN_SRC};branch=${QCOM_WLAN_SRCBRANCH};rev=${QCOM_WLAN_SRCREV};destsuffix=wlan/platform \
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

do_install:append() {
     mkdir -p ${D}/unstripped
     cp ${S}/wlan.ko ${D}/unstripped/wlan.ko
}

FILES:${PN}-dbg  = "/unstripped/*"

MAKE_TARGETS = "all"
MODULES_INSTALL_TARGET = "modules_install"
