inherit qprebuilt pkgconfig

LICENSE          = "Qualcomm-Technologies-Inc.-Proprietary"
LIC_FILES_CHKSUM = "file://${QCOM_COMMON_LICENSE_DIR}${LICENSE};md5=58d50a3d36f27f1a1e6089308a49b403"

DESCRIPTION = "sva-uv"

DEPENDS += "qcom-sva-sub-lib qcom-sva-common qcom-sva-eai-utils qcom-sva-eai qcom-sva-listen-common qcom-sva-gmm qcom-sva-swmad qcom-sva-listen-sound-model-headers"

PBT_ARCH = "armv8-2a"

SRC_URI[armv8-2a.sha256sum] = "058ed3d4922d2eade5497c389e7157dad052b859e3e91f9f6a46aa05d3d59274"

SRC_URI = "https://${PBT_ARTIFACTORY}/${PBT_BUILD_ID}/${PBT_BIN_PATH}/${BPN}_${PV}_${PBT_ARCH}.tar.gz;name=${PBT_ARCH}"
