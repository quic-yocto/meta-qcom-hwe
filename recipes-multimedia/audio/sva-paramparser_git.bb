inherit qprebuilt pkgconfig

LICENSE          = "Qualcomm-Technologies-Inc.-Proprietary"
LIC_FILES_CHKSUM = "file://${QCOM_COMMON_LICENSE_DIR}${LICENSE};md5=58d50a3d36f27f1a1e6089308a49b403"

DESCRIPTION = "sva-paramparser"

DEPENDS += "sva-listen-sound-model-headers sva-common"

PBT_ARCH = "armv8-2a"

SRC_URI[sha256sum] = "44341aadcdc269159825b879d456fa2ad2bbcee9fc4492f3c9a2420b4833d4cc"

SRC_URI = "https://${PBT_ARTIFACTORY}/${PBT_BUILD_ID}/${PBT_BIN_PATH}/${BPN}_${PV}_${PBT_ARCH}.tar.gz"
