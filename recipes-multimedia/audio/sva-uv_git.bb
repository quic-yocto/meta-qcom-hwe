inherit qprebuilt pkgconfig

LICENSE          = "Qualcomm-Technologies-Inc.-Proprietary"
LIC_FILES_CHKSUM = "file://${QCOM_COMMON_LICENSE_DIR}${LICENSE};md5=58d50a3d36f27f1a1e6089308a49b403"

DESCRIPTION = "sva-uv"

DEPENDS += "sva-sub-lib sva-common sva-eai-utils sva-eai sva-listen-common sva-gmm sva-swmad sva-listen-sound-model-headers"

PBT_ARCH = "armv8-2a"

SRC_URI[sha256sum] = "1423b8d0ac4ce54ea6c952ed6f462210764f6eed6897b04511ef71c23d6812b3"

SRC_URI = "https://${PBT_ARTIFACTORY}/${PBT_BUILD_ID}/${PBT_BIN_PATH}/${BPN}_${PV}_${PBT_ARCH}.tar.gz"
