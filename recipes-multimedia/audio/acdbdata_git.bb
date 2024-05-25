inherit qprebuilt pkgconfig

LICENSE          = "Qualcomm-Technologies-Inc.-Proprietary"
LIC_FILES_CHKSUM = "file://${QCOM_COMMON_LICENSE_DIR}${LICENSE};md5=58d50a3d36f27f1a1e6089308a49b403"

DESCRIPTION = "Audio Calibration Library"

DEPENDS += "mm-audio-headers"

PBT_ARCH = "armv8-2a"

SRC_URI[sha256sum] = "798c2a1e4712acfd166d1fdfb4f7512ce57f5cbb274f1a552be101c9ddc25b8c"

SRC_URI = "https://${PBT_ARTIFACTORY}/${PBT_BUILD_ID}/${PBT_BIN_PATH}/${BPN}_${PV}_${PBT_ARCH}.tar.gz"

SOLIBS = ".so"
FILES_SOLIBSDEV = ""
