inherit qprebuilt pkgconfig

LICENSE          = "Qualcomm-Technologies-Inc.-Proprietary"
LIC_FILES_CHKSUM = "file://${QCOM_COMMON_LICENSE_DIR}${LICENSE};md5=58d50a3d36f27f1a1e6089308a49b403"

DESCRIPTION = "sva-listen-sound-model"

DEPENDS += "sva-statereorder sva-listen-sound-model-headers sva-common sva-listen-common sva-gmm sva-swmad sva-uv sva-xs sva-eai sva-eai-utils sva-kwd-enroll sva-aml sva-ml-commondwarf2 sva-ml-commondwarf2-3 sva-paramparser sva-sub-lib sva-udk"

PBT_ARCH = "armv8-2a"

SRC_URI[armv8-2a.sha256sum] = "d3b96da135aee128ec46face21b78093583771d5ff7908a5d00fe0cad4341e14"

SRC_URI = "https://${PBT_ARTIFACTORY}/${PBT_BUILD_ID}/${PBT_BIN_PATH}/${BPN}_${PV}_${PBT_ARCH}.tar.gz;name=${PBT_ARCH}"

SOLIBS = ".so"
FILES_SOLIBSDEV = ""
