inherit module deploy

DESCRIPTION = "QCOM Camera device-tree"

LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/${LICENSE};md5=550794465ba0ec5312d6919e203a55f9"

SRC_URI += "git://git.codelinaro.org/clo/le/platform/vendor/opensource/camera-devicetree.git;protocol=https;rev=fc28373fa06ad58ad2df5c100e8659576fc6b9e8;branch=camera-kernel.qclinux.1.0.r1-rel;subdir=git/camera-devicetree \
           git://git.codelinaro.org/clo/le/platform/vendor/opensource/camera-kernel.git;protocol=https;rev=598d5c22f75685e8c897da4a612f805344d6617c;branch=camera-kernel.qclinux.1.0.r1-rel;subdir=git/camera-kernel \
           "

S = "${WORKDIR}/git/camera-devicetree"

DTC := "${KBUILD_OUTPUT}/scripts/dtc/dtc"
CAMERA_INCLUDE := "${S}/../camera-kernel/"
KERNEL_INCLUDE := "${STAGING_KERNEL_DIR}/include/"
EXTRA_OEMAKE += "DTC='${DTC}' CAMERA_INCLUDE='${CAMERA_INCLUDE}' KERNEL_INCLUDE='${KERNEL_INCLUDE}'"

do_compile() {
    oe_runmake ${EXTRA_OEMAKE} qcm6490-camera
    oe_runmake ${EXTRA_OEMAKE} qcm6490-camera-rb3
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
