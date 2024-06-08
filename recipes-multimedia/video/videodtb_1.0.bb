inherit module deploy

DESCRIPTION = "QCOM Video device-tree"

LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/${LICENSE};md5=550794465ba0ec5312d6919e203a55f9"

SRC_URI     =  "git://git.codelinaro.org/clo/le/platform/vendor/opensource/video-devicetree.git;protocol=https;rev=7bcad074d27d0b42644ff2501aa545b2d2e4f432;branch=video.qclinux.1.0.r1-rel"

S = "${WORKDIR}/git"

DTC := "${KBUILD_OUTPUT}/scripts/dtc/dtc"
KERNEL_INCLUDE := "${STAGING_KERNEL_DIR}/include/"
EXTRA_OEMAKE += "DTC='${DTC}' KERNEL_INCLUDE='${KERNEL_INCLUDE}'"

do_compile:qcm6490() {
    oe_runmake ${EXTRA_OEMAKE} qcm6490-video
}

do_compile:qcs9100() {
    oe_runmake ${EXTRA_OEMAKE} sa8775p-video
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
