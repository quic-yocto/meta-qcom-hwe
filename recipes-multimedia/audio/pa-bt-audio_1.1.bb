inherit qprebuilt pkgconfig

LICENSE          = "Qualcomm-Technologies-Inc.-Proprietary"
LIC_FILES_CHKSUM = "file://${QCOM_COMMON_LICENSE_DIR}${LICENSE};md5=58d50a3d36f27f1a1e6089308a49b403"

DESCRIPTION = "pulseaudio BT audio interface"

DEPENDS += "pulseaudio"

PBT_ARCH = "armv8-2a"

SRC_URI[sha256sum] = "407017129cf6d3a03303248a6580044820337f8e2bbda5a9458c483cdee9304e"

SRC_URI = "https://${PBT_ARTIFACTORY}/${PBT_BUILD_ID}/${PBT_BIN_PATH}/${BPN}_${PV}_${PBT_ARCH}.tar.gz"

SOLIBS = ".so"
FILES_SOLIBSDEV = ""
