inherit qprebuilt pkgconfig

LICENSE          = "Qualcomm-Technologies-Inc.-Proprietary"
LIC_FILES_CHKSUM = "file://${QCOM_COMMON_LICENSE_DIR}${LICENSE};md5=58d50a3d36f27f1a1e6089308a49b403"

DESCRIPTION = "sva-cnn"

DEPENDS += "sva-common sva-listen-common sva-swmad sva-ml-commondwarf sva-ml-commondwarf2 sva-ml-commondwarf2-3 sva-eai capiv2-headers sva-eai-utils pdk-wrapper"

PBT_ARCH = "armv8-2a"

SRC_URI[armv8-2a.sha256sum] = "add4289979e872701d0d82e3417969ce3d063adfed56ee64a040bf31a39358d4"

SRC_URI = "https://${PBT_ARTIFACTORY}/${PBT_BUILD_ID}/${PBT_BIN_PATH}/${BPN}_${PV}_${PBT_ARCH}.tar.gz;name=${PBT_ARCH}"

SOLIBS = ".so"
FILES_SOLIBSDEV = ""
