inherit module deploy

DESCRIPTION = "QCOM Camera device-tree"

LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/${LICENSE};md5=550794465ba0ec5312d6919e203a55f9"

SRC_URI     =  "git://git.codelinaro.org/clo/le/platform/vendor/opensource/camera-devicetree.git;protocol=https;rev=ab140c5c93e09ea73b114649c7b1d48743b28bf2;branch=camera-kernel.qclinux.1.0.r1-rel;destsuffix=vendor/qcom/opensource/camera-devicetree"
SRC_URI    +=  "git://git.codelinaro.org/clo/le/platform/vendor/opensource/camera-kernel.git;protocol=https;rev=aefae9a7897a2ca713b60472920a6c647a8962be;branch=camera-kernel.qclinux.1.0.r1-rel;destsuffix=vendor/qcom/opensource/camera-kernel"

S = "${WORKDIR}/vendor/qcom/opensource/camera-devicetree"

DTC := "${KBUILD_OUTPUT}/scripts/dtc/dtc"
CAMERA_INCLUDE ?= "${WORKDIR}/vendor/qcom/opensource/camera-kernel/camera"
CAMERA_INCLUDE:qcm6490 ?= "${WORKDIR}/vendor/qcom/opensource/camera-kernel/camera_kt"
KERNEL_INCLUDE := "${STAGING_KERNEL_DIR}/include/"
CAMERA_ARCH ?= "all"
CAMERA_ARCH:qcm6490 ?= "qcm6490"
EXTRA_OEMAKE += "CAMERA_ARCH='${CAMERA_ARCH}' MACHINE='${MACHINE}'"
EXTRA_OEMAKE += "DTC='${DTC}' CAMERA_INCLUDE='${CAMERA_INCLUDE}' KERNEL_INCLUDE='${KERNEL_INCLUDE}'"

do_compile() {
    oe_runmake ${EXTRA_OEMAKE} qcm6490-camera
    oe_runmake ${EXTRA_OEMAKE} qcm6490-camera-rb3
    oe_runmake ${EXTRA_OEMAKE} qcm5430-camera-rb3
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
