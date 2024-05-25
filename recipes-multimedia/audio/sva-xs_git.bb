inherit qprebuilt pkgconfig

LICENSE          = "Qualcomm-Technologies-Inc.-Proprietary"
LIC_FILES_CHKSUM = "file://${QCOM_COMMON_LICENSE_DIR}${LICENSE};md5=58d50a3d36f27f1a1e6089308a49b403"

DESCRIPTION = "sva-xs"

DEPENDS += "sva-common sva-eai-utils sva-eai sva-listen-common"

PBT_ARCH = "armv8-2a"

SRC_URI[sha256sum] = "0d8417639af76b0eb63537fb625b9e8dccfa9b02cd35aede2a370be1a460e4d9"

SRC_URI = "https://${PBT_ARTIFACTORY}/${PBT_BUILD_ID}/${PBT_BIN_PATH}/${BPN}_${PV}_${PBT_ARCH}.tar.gz"
