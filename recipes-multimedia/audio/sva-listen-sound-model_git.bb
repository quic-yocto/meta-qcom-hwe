inherit qprebuilt pkgconfig

LICENSE          = "Qualcomm-Technologies-Inc.-Proprietary"
LIC_FILES_CHKSUM = "file://${QCOM_COMMON_LICENSE_DIR}${LICENSE};md5=58d50a3d36f27f1a1e6089308a49b403"

DESCRIPTION = "sva-listen-sound-model"

DEPENDS += "sva-statereorder sva-listen-sound-model-headers sva-common sva-listen-common sva-gmm sva-swmad sva-uv sva-xs sva-eai sva-eai-utils sva-kwd-enroll sva-aml sva-ml-commondwarf2 sva-ml-commondwarf2-3 sva-paramparser sva-sub-lib sva-udk"

PBT_ARCH = "armv8-2a"

SRC_URI[sha256sum] = "6c0c5d8dc6b629ca145e33e928d6e0d26bfc090ea01253c8bb68e345e20af7b9"

SRC_URI = "https://${PBT_ARTIFACTORY}/${PBT_BUILD_ID}/${PBT_BIN_PATH}/${BPN}_${PV}_${PBT_ARCH}.tar.gz"

SOLIBS = ".so"
FILES_SOLIBSDEV = ""
