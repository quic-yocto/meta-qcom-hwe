inherit qprebuilt pkgconfig

LICENSE          = "Qualcomm-Technologies-Inc.-Proprietary"
LIC_FILES_CHKSUM = "file://${QCOM_COMMON_LICENSE_DIR}${LICENSE};md5=58d50a3d36f27f1a1e6089308a49b403"

DESCRIPTION = "sva-uv"

DEPENDS += "sva-common sva-eai-utils sva-eai mm-audio-headers"

PBT_ARCH = "armv8-2a"

SRC_URI[sha256sum] = "a68af8560bc97dd025df0a3c1ceef29f44110e8b6dfbb4347357dfab32f80acd"

SRC_URI = "https://${PBT_ARTIFACTORY}/${PBT_BUILD_ID}/${PBT_BIN_PATH}/${BPN}_${PV}_${PBT_ARCH}.tar.gz"
