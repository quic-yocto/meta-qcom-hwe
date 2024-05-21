inherit qprebuilt pkgconfig

LICENSE          = "Qualcomm-Technologies-Inc.-Proprietary"
LIC_FILES_CHKSUM = "file://${QCOM_COMMON_LICENSE_DIR}${LICENSE};md5=58d50a3d36f27f1a1e6089308a49b403"

DESCRIPTION = "Edglite Config XML parser"

DEPENDS += "pal-headers mm-audio-headers args"

PBT_ARCH = "armv8-2a"

SRC_URI[sha256sum] = "0c654556c9e760ce7c013b9eab6379796a6c9134e6b61723cd3d215ab071e9c3"

SRC_URI = "https://${PBT_ARTIFACTORY}/${PBT_BUILD_ID}/${PBT_BIN_PATH}/${BPN}_${PV}_${PBT_ARCH}.tar.gz"

SOLIBS = ".so"
FILES_SOLIBSDEV = ""
