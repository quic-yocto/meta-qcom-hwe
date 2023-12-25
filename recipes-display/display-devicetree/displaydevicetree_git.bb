DESCRIPTION = "QCOM Display devicetree"
LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/\
${LICENSE};md5=550794465ba0ec5312d6919e203a55f9"

inherit module deploy

SRC_URI = "git://git.codelinaro.org/clo/le/platform/vendor/opensource/display-devicetree.git;protocol=https;rev=00d3210ec735c7ee8fca8fc4181684913230ee45;branch=display-kernel.qclinux.1.0.r2-rel"

S = "${WORKDIR}/git"

DTC := "${KBUILD_OUTPUT}/scripts/dtc/dtc"
KERNEL_INCLUDE := "${STAGING_KERNEL_DIR}/include/"
EXTRA_OEMAKE += "DTC='${DTC}' KERNEL_INCLUDE='${KERNEL_INCLUDE}'"

do_install() {
   :
}

do_compile() {
    oe_runmake ${EXTRA_OEMAKE} qcm6490-display
    oe_runmake ${EXTRA_OEMAKE} qcm6490-display-rb3
}

do_deploy() {
    install -d ${DEPLOYDIR}/tech_dtbs
    install -m 0644 \
    ${S}/display/*.dtbo \
    ${DEPLOYDIR}/tech_dtbs/
}
addtask do_deploy after do_install
