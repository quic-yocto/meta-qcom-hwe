DESCRIPTION = "QCOM graphics devicetree"
LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/files/common-licenses/${LICENSE};md5=801f80980d171dd6425610833a22dbe6"

inherit module deploy

SRC_URI = "git://git.codelinaro.org/clo/le/platform/vendor/qcom/opensource/graphics-devicetree.git;protocol=https;rev=cfe9519cbbb1b063260e5b45a4eddf237ebe65bc;branch=gfx-kernel.le.0.0.r1-rel"

S = "${WORKDIR}/git"

DTC := "${KBUILD_OUTPUT}/scripts/dtc/dtc"
KERNEL_INCLUDE := "${STAGING_KERNEL_DIR}/include/"
EXTRA_OEMAKE += "DTC='${DTC}' KERNEL_INCLUDE='${KERNEL_INCLUDE}'"

do_install() {
   :
}

do_compile() {
    oe_runmake ${EXTRA_OEMAKE} qcm6490-graphics
}

do_deploy() {
    install -d ${DEPLOYDIR}/tech_dtbs
    install -m 0644 \
    ${S}/gpu/*.dtbo \
    ${DEPLOYDIR}/tech_dtbs/
}

addtask do_deploy after do_install
RM_WORK_EXCLUDE += "${PN}"

