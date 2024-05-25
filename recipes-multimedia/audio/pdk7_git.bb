inherit qprebuilt pkgconfig

LICENSE          = "Qualcomm-Technologies-Inc.-Proprietary"
LIC_FILES_CHKSUM = "file://${QCOM_COMMON_LICENSE_DIR}${LICENSE};md5=58d50a3d36f27f1a1e6089308a49b403"

DESCRIPTION = "pdk7"

DEPENDS += "sva-common capiv2-headers sva-ml-commondwarf sva-ml-commondwarf2 sva-ml-commondwarf2-3 sva-eai sva-eai-utils pdk-wrapper-headers"

PBT_ARCH = "armv8-2a"

SRC_URI[sha256sum] = "12d7094b3c8e8a027a1927bbf605931e5f7a4d4d3209e671fd5b7eb35de5d65d"

SRC_URI = "https://${PBT_ARTIFACTORY}/${PBT_BUILD_ID}/${PBT_BIN_PATH}/${BPN}_${PV}_${PBT_ARCH}.tar.gz"

SOLIBS = ".so"
FILES_SOLIBSDEV = ""
