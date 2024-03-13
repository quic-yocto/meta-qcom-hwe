DESCRIPTION = "QCOM EVA devicetree"
LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/${LICENSE};md5=550794465ba0ec5312d6919e203a55f9"

inherit module deploy
SRC_URI += "git://git.codelinaro.org/clo/le/platform/vendor/opensource/eva-kernel.git;protocol=https;rev=aa5d4d1d1a91815fc0508d25b6b0e4d2523cbdd1;branch=eva-kernel.qclinux.1.0.r1-rel;destsuffix=cv/cv-kernel/eva-devicetree"
S = "${WORKDIR}/cv/cv-kernel/eva-devicetree"

DTC := "${KBUILD_OUTPUT}/scripts/dtc/dtc"
KERNEL_INCLUDE := "${STAGING_KERNEL_DIR}/include/"
EXTRA_OEMAKE += "DTC='${DTC}' KERNEL_INCLUDE='${KERNEL_INCLUDE}'"

do_install() {
   :
}

do_compile() {
    oe_runmake ${EXTRA_OEMAKE} qcs8550-eva
}

do_deploy() {
    install -d ${DEPLOYDIR}/tech_dtbs
    install -m 0644 ${S}/eva/*.dtbo ${DEPLOYDIR}/tech_dtbs/
}
addtask do_deploy after do_install
