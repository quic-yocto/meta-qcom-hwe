inherit qprebuilt pkgconfig

LICENSE          = "Qualcomm-Technologies-Inc.-Proprietary"
LIC_FILES_CHKSUM = "file://${QCOM_COMMON_LICENSE_DIR}${LICENSE};md5=58d50a3d36f27f1a1e6089308a49b403"

DESCRIPTION = "pdk7"

DEPENDS += "qcom-sva-common qcom-capiv2-headers qcom-sva-ml-commondwarf qcom-sva-ml-commondwarf2 qcom-sva-ml-commondwarf2-3 qcom-sva-eai qcom-sva-eai-utils qcom-pdk-wrapper-headers"

PBT_ARCH = "armv8-2a"

SRC_URI[armv8-2a.sha256sum] = "2f080e0f9cb48eb7fb2cd6fea52374a05368ddd72fd4cb36bb01a6ef85c69fc0"

SRC_URI = "https://${PBT_ARTIFACTORY}/${PBT_BUILD_ID}/${PBT_BIN_PATH}/${BPN}_${PV}_${PBT_ARCH}.tar.gz;name=${PBT_ARCH}"

SOLIBS = ".so"
FILES_SOLIBSDEV = ""
