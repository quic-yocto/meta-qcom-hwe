DESCRIPTION = "QCOM Display devicetree"
LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/\
${LICENSE};md5=550794465ba0ec5312d6919e203a55f9"

inherit module deploy
SRC_URI     =  "git://git.codelinaro.org/clo/le/platform/vendor/opensource/display-devicetree.git;protocol=https;rev=8796b80fdfda8c9e2bfc45a2e8624b237f817e08;branch=display-kernel.qclinux.1.0.r2-rel;destsuffix=display/vendor/qcom/opensource/display-devicetree"
S = "${WORKDIR}/display/vendor/qcom/opensource/display-devicetree"

DTC := "${KBUILD_OUTPUT}/scripts/dtc/dtc"
KERNEL_INCLUDE := "${STAGING_KERNEL_DIR}/include/"
EXTRA_OEMAKE += "DTC='${DTC}' KERNEL_INCLUDE='${KERNEL_INCLUDE}'"

do_install() {
   :
}

do_compile() {
    oe_runmake ${EXTRA_OEMAKE} qcm6490-display
    oe_runmake ${EXTRA_OEMAKE} qcm6490-display-rb3
    oe_runmake ${EXTRA_OEMAKE} qcs8550-display
    oe_runmake ${EXTRA_OEMAKE} sa8775p-display
}

do_deploy() {
    install -d ${DEPLOYDIR}/tech_dtbs
    install -m 0644 \
    ${S}/display/*.dtbo \
    ${DEPLOYDIR}/tech_dtbs/
}
addtask do_deploy after do_install