inherit qprebuilt pkgconfig

LICENSE          = "Qualcomm-Technologies-Inc.-Proprietary"
LIC_FILES_CHKSUM = "file://${QCOM_COMMON_LICENSE_DIR}${LICENSE};md5=58d50a3d36f27f1a1e6089308a49b403"

DESCRIPTION = "sva-statereorder"

DEPENDS += "sva-listen-sound-model-headers sva-common sva-listen-common"

PBT_ARCH = "armv8-2a"

SRC_URI[sha256sum] = "fa371a3e257a8dc456f6bd5f04e7e69e62352525a4233f36cd73f82da22e4f03"

SRC_URI = "https://${PBT_ARTIFACTORY}/${PBT_BUILD_ID}/${PBT_BIN_PATH}/${BPN}_${PV}_${PBT_ARCH}.tar.gz;name=${PBT_ARCH}"
