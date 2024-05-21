inherit qprebuilt pkgconfig

LICENSE          = "Qualcomm-Technologies-Inc.-Proprietary"
LIC_FILES_CHKSUM = "file://${QCOM_COMMON_LICENSE_DIR}${LICENSE};md5=58d50a3d36f27f1a1e6089308a49b403"

DESCRIPTION = "Voice UI test app"

DEPENDS += "pulseaudio glib-2.0"

PBT_ARCH = "armv8-2a"

SRC_URI[sha256sum] = "7359e50a68c89bbd74fa43dc9dd39f1e1debbc40c5d2d69b48b840b07614a9d4"

SRC_URI = "https://${PBT_ARTIFACTORY}/${PBT_BUILD_ID}/${PBT_BIN_PATH}/${BPN}_${PV}_${PBT_ARCH}.tar.gz"

SOLIBS = ".so"
FILES_SOLIBSDEV = ""
