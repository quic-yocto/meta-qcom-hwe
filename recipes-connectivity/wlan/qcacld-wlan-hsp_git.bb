inherit module deploy

DESCRIPTION = "Qualcomm Technologies, Inc. WLAN CLD3.0 low latency driver"
LICENSE = "ISC & GPL-2.0-only"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/ISC;md5=f3b90e78ea0cffb20bf5cca7947a896d \
                    file://${COMMON_LICENSE_DIR}/GPL-2.0-only;md5=801f80980d171dd6425610833a22dbe6"

DEPENDS += "wlan-platform qcacld-wlan"


MODULE_NAME = "qca6490"

MODULE_NAME:qcm6490 := "qca6490"

SRC_URI = "git://git.codelinaro.org/clo/le/platform/vendor/qcom-opensource/wlan/qcacld-3.0.git;protocol=https;rev=d61eba677e5f5aea6a0dc89b7c0664a63b51f485;branch=wlan-cld3.driver.lnx.2.0.14.r1-rel;destsuffix=wlan/qcacld-3.0"
SRC_URI += "git://git.codelinaro.org/clo/le/platform/vendor/qcom-opensource/wlan/qca-wifi-host-cmn.git;protocol=https;rev=3e4a835eada76b5fac596c8d43d67ae975af7145;branch=wlan-cmn.driver.lnx.2.0.14.r1-rel;destsuffix=wlan/qca-wifi-host-cmn"
SRC_URI += "git://git.codelinaro.org/clo/le/platform/vendor/qcom-opensource/wlan/fw-api.git;protocol=https;rev=e3a9e8051ec8a0c79fe4a1d932f234cf8dfa9565;branch=wlan-api.lnx.1.0.r230-rel;destsuffix=wlan/fw-api"
SRC_URI += "git://git.codelinaro.org/clo/le/platform/vendor/qcom-opensource/wlan/platform.git;protocol=https;rev=fe1d44e365c52cc66c4a39f48cd0919de4b72dee;branch=wlan-platform.qclinux.1.0.r2-rel;destsuffix=wlan/platform"
SRC_URI += "file://qcacld-kbuild.patch"

S = "${WORKDIR}/wlan/qcacld-3.0"

RPROVIDES:${PN} += "kernel-module-${MODULE_NAME}"

EXTRA_OEMAKE += "MACHINE='${MACHINE}'"
EXTRA_OEMAKE += "CONFIG_CNSS_OUT_OF_TREE=y"
EXTRA_OEMAKE += "CONFIG_CNSS_QMI_SVC=m"
EXTRA_OEMAKE += "CONFIG_CNSS_PLAT_IPC_QMI_SVC=m"
EXTRA_OEMAKE += "CONFIG_CNSS_GENL=m"

python __anonymous () {
    d.setVar('KBUILD_EXTRA_SYMBOLS', d.getVar('STAGING_DIR_HOST') + "/usr/include"  + "/wlan-platform/Module.symvers" )
    machine = d.getVar('MACHINE')
    if "qcs6490" in machine or "qcm6490" in machine:
        d.appendVar('EXTRA_OEMAKE', " CONFIG_CNSS_QCA6490=y")
        d.appendVar('EXTRA_OEMAKE', " CONFIG_CNSS2=m")
        d.appendVar('EXTRA_OEMAKE', " CONFIG_CNSS2_QMI=y")
        d.appendVar('EXTRA_OEMAKE', " CONFIG_CNSS_UTILS=m")
        d.appendVar('EXTRA_OEMAKE', " CONFIG_WCNSS_MEM_PRE_ALLOC=m")
        d.appendVar('EXTRA_OEMAKE', " WLAN_CTRL_NAME=qca6490")
        d.appendVar('EXTRA_OEMAKE', " CONFIG_QCA_CLD_WLAN_PROFILE=qca6490")
        d.appendVar('EXTRA_OEMAKE', " MODNAME=${MODULE_NAME}")
}

do_patch() {
     cd ${S}
     patch -p1 < ${WORKDIR}/qcacld-kbuild.patch
}

do_install:append() {
     mkdir -p ${D}/unstripped
     cp ${S}/${MODULE_NAME}.ko ${D}/unstripped/${MODULE_NAME}.ko
}

FILES:${PN}-dbg  = "/unstripped/*"

MAKE_TARGETS = "all"
MODULES_INSTALL_TARGET = "modules_install"
