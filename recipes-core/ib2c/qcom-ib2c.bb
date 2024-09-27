inherit qprebuilt

LICENSE          = "Qualcomm-Technologies-Inc.-Proprietary"
LIC_FILES_CHKSUM = "file://${QCOM_COMMON_LICENSE_DIR}${LICENSE};md5=58d50a3d36f27f1a1e6089308a49b403"

DESCRIPTION = "QCOM library image blending, color convertion and composition."

DEPENDS += "virtual/kernel virtual/egl virtual/libgles2 gbm"

PBT_ARCH = "armv8-2a"

SRC_URI[armv8-2a.sha256sum] = "b5c949c742d4cbd65007d19c0ba971534e4663db66667278f388539c2ba22aaf"

SRC_URI = "https://${PBT_ARTIFACTORY}/${PBT_BUILD_ID}/${PBT_BIN_PATH}/${BPN}_${PV}_${PBT_ARCH}.tar.gz;name=${PBT_ARCH}"

FILES:${PN} += "${INSTALL_BINDIR}"
FILES:${PN} += "${INSTALL_LIBDIR}"


SOLIBS = ".so*"
FILES_SOLIBSDEV = ""
