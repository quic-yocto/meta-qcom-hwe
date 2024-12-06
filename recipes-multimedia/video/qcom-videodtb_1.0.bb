inherit module deploy

DESCRIPTION = "QCOM Video device-tree"

LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/${LICENSE};md5=550794465ba0ec5312d6919e203a55f9"

SRCPROJECT = "git://git.codelinaro.org/clo/le/platform/vendor/opensource/video-devicetree.git;protocol=https"
SRCBRANCH  = "video.qclinux.1.0.r1-rel"
SRCREV     = "926c4c6548a0296369fdfd48b3208e6b5788179e"

SRC_URI = "${SRCPROJECT};branch=${SRCBRANCH};destsuffix=vendor/qcom/opensource/video-devicetree"

S = "${WORKDIR}/vendor/qcom/opensource/video-devicetree"

DTC := "${KBUILD_OUTPUT}/scripts/dtc/dtc"
KERNEL_INCLUDE := "${STAGING_KERNEL_DIR}/include/"
EXTRA_OEMAKE += "DTC='${DTC}' KERNEL_INCLUDE='${KERNEL_INCLUDE}'"

do_compile:qcm6490() {
    oe_runmake ${EXTRA_OEMAKE} qcm6490-video
}

do_compile:qcs9100() {
    oe_runmake ${EXTRA_OEMAKE} sa8775p-video
}

do_compile:qcs8300() {
    oe_runmake ${EXTRA_OEMAKE} qcs8300-video
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
