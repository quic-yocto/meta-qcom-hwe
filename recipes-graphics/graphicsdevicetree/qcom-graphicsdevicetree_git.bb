DESCRIPTION = "QCOM graphics devicetree"
LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/${LICENSE};md5=801f80980d171dd6425610833a22dbe6"

inherit module deploy

SRC_URI     =  "git://git.codelinaro.org/clo/le/platform/vendor/qcom/opensource/graphics-devicetree.git;protocol=https;rev=94f56dbb81e58c862c50b9c945aa42ffe7ba216e;branch=gfx-kernel.le.0.0.r1-rel;destsuffix=graphics-devicetree"

S = "${WORKDIR}/graphics-devicetree"

PROVIDES += "graphicsdevicetree"

DTC := "${KBUILD_OUTPUT}/scripts/dtc/dtc"
GRAPHICS_INCLUDE := "${WORKSPACE}/graphics-kernel/"
KERNEL_INCLUDE := "${STAGING_KERNEL_DIR}/include/"
EXTRA_OEMAKE += "DTC='${DTC}' GRAPHICS_INCLUDE='${GRAPHICS_INCLUDE}' KERNEL_INCLUDE='${KERNEL_INCLUDE}'"

do_install() {
   :
}

do_compile() {
    oe_runmake ${EXTRA_OEMAKE} qcs5430-graphics
    oe_runmake ${EXTRA_OEMAKE} qcs5430-fp2p5-graphics
    oe_runmake ${EXTRA_OEMAKE} qcm6490-graphics
    oe_runmake ${EXTRA_OEMAKE} qcs8550-graphics
    oe_runmake ${EXTRA_OEMAKE} qcs9100-graphics
}

do_deploy() {
    install -d ${DEPLOYDIR}/tech_dtbs
    install -m 0644 \
    ${S}/gpu/*.dtbo \
    ${DEPLOYDIR}/tech_dtbs/
}

addtask do_deploy after do_install
