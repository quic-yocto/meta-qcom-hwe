inherit qprebuilt pkgconfig

LICENSE          = "Qualcomm-Technologies-Inc.-Proprietary"
LIC_FILES_CHKSUM = "file://${QCOM_COMMON_LICENSE_DIR}${LICENSE};md5=58d50a3d36f27f1a1e6089308a49b403"

DESCRIPTION = "pulseaudio BT audio interface"

DEPENDS += "pulseaudio"

PBT_ARCH = "armv8-2a"

SRC_URI[sha256sum] = "165f885f6c17717fdd74cdc0cd7c4ff240b0b0a8ba65f82a4b55221075d78bc1"

SRC_URI = "https://${PBT_ARTIFACTORY}/${PBT_BUILD_ID}/${PBT_BIN_PATH}/${BPN}_${PV}_${PBT_ARCH}.tar.gz;name=${PBT_ARCH}"

SOLIBS = ".so"
FILES_SOLIBSDEV = ""
