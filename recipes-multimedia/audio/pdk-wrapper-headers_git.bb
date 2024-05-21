inherit qprebuilt pkgconfig

LICENSE          = "Qualcomm-Technologies-Inc.-Proprietary"
LIC_FILES_CHKSUM = "file://${QCOM_COMMON_LICENSE_DIR}${LICENSE};md5=58d50a3d36f27f1a1e6089308a49b403"

DESCRIPTION = "pdk wrapper"

PBT_ARCH = "armv8-2a"

SRC_URI[sha256sum] = "bcad743caa800fe4413b991d937705ce5102c6def3f353949ace85b199d42a9a"

SRC_URI = "https://${PBT_ARTIFACTORY}/${PBT_BUILD_ID}/${PBT_BIN_PATH}/${BPN}_${PV}_${PBT_ARCH}.tar.gz"

SOLIBS = ".so"
FILES_SOLIBSDEV = ""
