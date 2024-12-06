DESCRIPTION = "QCOM BT devicetree"
LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/${LICENSE};md5=550794465ba0ec5312d6919e203a55f9"

inherit module deploy

QCOM_BT_DT_SRC ?= "git://git.codelinaro.org/clo/le/platform/vendor/qcom-opensource/bt-devicetree.git;protocol=https"
QCOM_BT_DT_SRCBRANCH ?= "bt-performant.qclinux.1.0.r1-rel"
QCOM_BT_DT_SRCREV ?= "bf9d4e3a5139a919099ace5978317e9e6f129507"

QCOM_BLUETOOTH_EXT_SRC ?= "git://git.codelinaro.org/clo/le/platform/vendor/qcom-opensource/bluetooth_ext.git;protocol=https"
QCOM_BLUETOOTH_EXT_SRCBRANCH ?= "bt-performant.qclinux.1.0.r1-rel"
QCOM_BLUETOOTH_EXT_SRCREV ?= "996bbb712e62c5c22489cd84fad1a93a91d65ddc"

SRC_URI = "${QCOM_BT_DT_SRC};branch=${QCOM_BT_DT_SRCBRANCH};rev=${QCOM_BT_DT_SRCREV};destsuffix=bluetooth/bt-devicetree \
           ${QCOM_BLUETOOTH_EXT_SRC};branch=${QCOM_BLUETOOTH_EXT_SRCBRANCH};rev=${QCOM_BLUETOOTH_EXT_SRCREV};destsuffix=bluetooth/stack/bluetooth_ext"

BT_SOURCE = "${WORKDIR}/bluetooth"
S = "${BT_SOURCE}/bt-devicetree"
S_EXT = "${BT_SOURCE}/stack/bluetooth_ext/system_bt_ext"

DEPENDS += "virtual/kernel"

DTC := "${KBUILD_OUTPUT}/scripts/dtc/dtc"
KERNEL_INCLUDE := "${STAGING_KERNEL_DIR}/include/"
EXTRA_OEMAKE += "DTC='${DTC}' KERNEL_INCLUDE='${KERNEL_INCLUDE}'"

do_compile() {
    oe_runmake ${EXTRA_OEMAKE} qcm6490-bt
    oe_runmake ${EXTRA_OEMAKE} qcm6490-bt-rb3-hsp
}

do_install() {
    :
}

do_deploy() {
    echo "DTBO Staging path -> " ${DEPLOYDIR}/tech_dtbs
    install -d ${DEPLOYDIR}/tech_dtbs
    install -m 0644 ${S}/*.dtbo ${DEPLOYDIR}/tech_dtbs
}

addtask do_deploy after do_install

