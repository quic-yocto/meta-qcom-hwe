inherit qprebuilt pkgconfig

LICENSE          = "Qualcomm-Technologies-Inc.-Proprietary"
LIC_FILES_CHKSUM = "file://${QCOM_COMMON_LICENSE_DIR}${LICENSE};md5=58d50a3d36f27f1a1e6089308a49b403"

DESCRIPTION = "sva-listen-sound-model"

DEPENDS += "sva-statereorder sva-listen-sound-model-headers sva-common sva-listen-common sva-gmm sva-swmad sva-uv sva-xs sva-eai sva-eai-utils sva-kwd-enroll sva-aml sva-ml-commondwarf2 sva-ml-commondwarf2-3 sva-paramparser sva-sub-lib sva-udk"

PBT_ARCH = "armv8-2a"

SRC_URI[sha256sum] = "80bd161fc98fb048c92a0dd076991c718ff941672af7cd96573058a74c54ed7c"

SRC_URI = "https://${PBT_ARTIFACTORY}/${PBT_BUILD_ID}/${PBT_BIN_PATH}/${BPN}_${PV}_${PBT_ARCH}.tar.gz;name=${PBT_ARCH}"

SOLIBS = ".so"
FILES_SOLIBSDEV = ""
