inherit qprebuilt pkgconfig

LICENSE          = "Qualcomm-Technologies-Inc.-Proprietary"
LIC_FILES_CHKSUM = "file://${QCOM_COMMON_LICENSE_DIR}${LICENSE};md5=58d50a3d36f27f1a1e6089308a49b403"

DESCRIPTION = "Voice UI test app"

DEPENDS += "pulseaudio glib-2.0 pal vui-interface-header"

PBT_ARCH = "armv8-2a"

SRC_URI[armv8-2a.sha256sum] = "77d475894e06da15b27f9e91a3f2d3ac788e44e0d1fe6de408ce523427c078b7"

SRC_URI = "https://${PBT_ARTIFACTORY}/${PBT_BUILD_ID}/${PBT_BIN_PATH}/${BPN}_${PV}_${PBT_ARCH}.tar.gz;name=${PBT_ARCH}"

SOLIBS = ".so"
FILES_SOLIBSDEV = ""
