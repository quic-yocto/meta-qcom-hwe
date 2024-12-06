inherit module deploy

DESCRIPTION = "QCOM QPS615 devicetree"
LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/files/common-licenses/${LICENSE};md5=550794465ba0ec5312d6919e203a55f9"

SRCPROJECT = "git://git.codelinaro.org/clo/le/platform/vendor/opensource/data-eth.git;protocol=https"
SRCBRANCH  = "data-kernel.qclinux.1.0.r1-rel"
SRCREV     = "5f1f52ca95a7d1ea0ab83ea65f7381c8bc8a863e"

SRC_URI =  "${SRCPROJECT};branch=${SRCBRANCH};destsuffix=data-eth"

S = "${WORKDIR}/data-eth/ethernet-devicetree"

DTC := "${KBUILD_OUTPUT}/scripts/dtc/dtc"
KERNEL_INCLUDE := "${STAGING_KERNEL_DIR}/include/"
EXTRA_OEMAKE += "DTC='${DTC}' KERNEL_INCLUDE='${KERNEL_INCLUDE}'"

do_install() {
   :
}

do_compile() {
    oe_runmake ${EXTRA_OEMAKE} qcs8550-qps615
}

do_deploy() {
    install -d ${DEPLOYDIR}/tech_dtbs
    install -m 0644  ${S}/*.dtbo ${DEPLOYDIR}/tech_dtbs
}
addtask do_deploy after do_install
