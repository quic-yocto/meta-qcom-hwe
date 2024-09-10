inherit qprebuilt pkgconfig

LICENSE          = "Qualcomm-Technologies-Inc.-Proprietary"
LIC_FILES_CHKSUM = "file://${QCOM_COMMON_LICENSE_DIR}${LICENSE};md5=58d50a3d36f27f1a1e6089308a49b403"

DESCRIPTION = "sva-listen-sound-model"

DEPENDS += "qcom-sva-statereorder qcom-sva-listen-sound-model-headers qcom-sva-common qcom-sva-listen-common qcom-sva-gmm qcom-sva-swmad qcom-sva-uv qcom-sva-xs qcom-sva-eai qcom-sva-eai-utils qcom-sva-kwd-enroll qcom-sva-aml qcom-sva-ml-commondwarf2 qcom-sva-ml-commondwarf2-3 qcom-sva-paramparser qcom-sva-sub-lib qcom-sva-udk"

PBT_ARCH = "armv8-2a"

SRC_URI[armv8-2a.sha256sum] = "3e167ccfb4962351f36434379318dc864ac5a9fa416e6294f3b3832e185d1e25"

SRC_URI = "https://${PBT_ARTIFACTORY}/${PBT_BUILD_ID}/${PBT_BIN_PATH}/${BPN}_${PV}_${PBT_ARCH}.tar.gz;name=${PBT_ARCH}"

SOLIBS = ".so"
FILES_SOLIBSDEV = ""
