inherit qprebuilt pkgconfig

LICENSE          = "Qualcomm-Technologies-Inc.-Proprietary"
LIC_FILES_CHKSUM = "file://${QCOM_COMMON_LICENSE_DIR}${LICENSE};md5=58d50a3d36f27f1a1e6089308a49b403"

DESCRIPTION = "Voice UI test app"

DEPENDS += "pulseaudio glib-2.0 qcom-pal qcom-vui-interface-header"

PBT_ARCH = "armv8-2a"

SRC_URI[armv8-2a.sha256sum] = "d5573666ea733d3e3a05e8cfbdda89908d83aae21b46d3079c0775f124186638"

SRC_URI = "https://${PBT_ARTIFACTORY}/${PBT_BUILD_ID}/${PBT_BIN_PATH}/${BPN}_${PV}_${PBT_ARCH}.tar.gz;name=${PBT_ARCH}"

SOLIBS = ".so"
FILES_SOLIBSDEV = ""
