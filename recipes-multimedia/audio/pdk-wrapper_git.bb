inherit qprebuilt pkgconfig

LICENSE          = "Qualcomm-Technologies-Inc.-Proprietary"
LIC_FILES_CHKSUM = "file://${QCOM_COMMON_LICENSE_DIR}${LICENSE};md5=58d50a3d36f27f1a1e6089308a49b403"

DESCRIPTION = "pdk wrapper"

DEPENDS += "sva-common capiv2-headers sva-ml-commondwarf sva-ml-commondwarf2 sva-ml-commondwarf2-3 sva-eai sva-eai-utils pdk3 pdk4 pdk5 pdk7 pdk-wrapper-headers"

PBT_ARCH = "armv8-2a"

SRC_URI[sha256sum] = "6a9edc9b845fc5b3718f62b32f3eb50e67edd11511ae4ab6f3c9e19f5b2a3c8d"

SRC_URI = "https://${PBT_ARTIFACTORY}/${PBT_BUILD_ID}/${PBT_BIN_PATH}/${BPN}_${PV}_${PBT_ARCH}.tar.gz"

SOLIBS = ".so"
FILES_SOLIBSDEV = ""
