inherit qprebuilt pkgconfig

LICENSE          = "Qualcomm-Technologies-Inc.-Proprietary"
LIC_FILES_CHKSUM = "file://${QCOM_COMMON_LICENSE_DIR}${LICENSE};md5=58d50a3d36f27f1a1e6089308a49b403"

DESCRIPTION = "sva-cnn"

DEPENDS += "qcom-sva-common qcom-sva-listen-common qcom-sva-swmad qcom-sva-ml-commondwarf qcom-sva-ml-commondwarf2 qcom-sva-ml-commondwarf2-3 qcom-sva-eai qcom-capiv2-headers qcom-sva-eai-utils qcom-pdk-wrapper"

PBT_ARCH = "armv8-2a"

SRC_URI[armv8-2a.sha256sum] = "d984243e15cc788eac5e00581ea6072a8f9b4268ec6ae62bfd9508eab9cb9ec5"

SRC_URI = "https://${PBT_ARTIFACTORY}/${PBT_BUILD_ID}/${PBT_BIN_PATH}/${BPN}_${PV}_${PBT_ARCH}.tar.gz;name=${PBT_ARCH}"

SOLIBS = ".so"
FILES_SOLIBSDEV = ""
