DESCRIPTION = "QCOM BT devicetree"
LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/${LICENSE};md5=550794465ba0ec5312d6919e203a55f9"

inherit module deploy

SRC_URI += "git://git.codelinaro.org/clo/le/platform/vendor/qcom-opensource/bt-devicetree.git;protocol=https;rev=c326166c78890b12578cc302e2f21ae32b79286f;branch=bt-performant.qclinux.1.0.r1-rel"

BT_SOURCE = "${WORKDIR}"
S = "${WORKDIR}/git"
S_EXT = "${BT_SOURCE}/stack/bluetooth_ext/system_bt_ext"

DEPENDS += "virtual/kernel"

DTC := "${KBUILD_OUTPUT}/scripts/dtc/dtc"
KERNEL_INCLUDE := "${STAGING_KERNEL_DIR}/include/"
EXTRA_OEMAKE += "DTC='${DTC}' KERNEL_INCLUDE='${KERNEL_INCLUDE}'"

do_compile() {
    oe_runmake ${EXTRA_OEMAKE} qcm6490-bt
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

