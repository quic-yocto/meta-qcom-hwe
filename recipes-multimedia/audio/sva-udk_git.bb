inherit qprebuilt pkgconfig

LICENSE          = "Qualcomm-Technologies-Inc.-Proprietary"
LIC_FILES_CHKSUM = "file://${QCOM_COMMON_LICENSE_DIR}${LICENSE};md5=58d50a3d36f27f1a1e6089308a49b403"

DESCRIPTION = "sva-uv"

DEPENDS += "sva-common sva-eai-utils sva-eai vui-interface-header"

PBT_ARCH = "armv8-2a"

SRC_URI[sha256sum] = "444a1553f342d52627ffeda8b67b410a712f2d26ce6163d7d01284a741542cd4"

SRC_URI = "https://${PBT_ARTIFACTORY}/${PBT_BUILD_ID}/${PBT_BIN_PATH}/${BPN}_${PV}_${PBT_ARCH}.tar.gz;name=${PBT_ARCH}"
