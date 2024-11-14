inherit qprebuilt pkgconfig

LICENSE          = "Qualcomm-Technologies-Inc.-Proprietary"
LIC_FILES_CHKSUM = "file://${QCOM_COMMON_LICENSE_DIR}${LICENSE};md5=58d50a3d36f27f1a1e6089308a49b403"

DESCRIPTION = "sva-listen-sound-model"

DEPENDS += "qcom-sva-statereorder qcom-sva-listen-sound-model-headers qcom-sva-common qcom-sva-listen-common qcom-sva-gmm qcom-sva-swmad qcom-sva-uv qcom-sva-xs qcom-sva-eai qcom-sva-eai-utils qcom-sva-kwd-enroll qcom-sva-aml qcom-sva-ml-commondwarf2 qcom-sva-ml-commondwarf2-3 qcom-sva-paramparser qcom-sva-sub-lib qcom-sva-udk"

PBT_ARCH = "armv8-2a"

ARMV8_SHA256SUM = "3d0b2daf0a53c40baac01b3b1e35875310bcaa2518450c14a984f9a0a700d15f"

SRC_URI[armv8-2a.sha256sum] = "${ARMV8_SHA256SUM}"

SRC_URI = "https://${PBT_ARTIFACTORY}/${PBT_BUILD_ID}/${PBT_BIN_PATH}/${BPN}_${PV}_${PBT_ARCH}.tar.gz;name=${PBT_ARCH}"

SOLIBS = ".so"
FILES_SOLIBSDEV = ""
