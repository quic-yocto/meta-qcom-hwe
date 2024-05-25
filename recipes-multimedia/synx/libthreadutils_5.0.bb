inherit qprebuilt pkgconfig systemd

LICENSE          = "Qualcomm-Technologies-Inc.-Proprietary"
LIC_FILES_CHKSUM = "file://${QCOM_COMMON_LICENSE_DIR}${LICENSE};md5=58d50a3d36f27f1a1e6089308a49b403"

DESCRIPTION = "Generates synx threadutils lib"

DEPENDS += "libos glib-2.0"

SRC_URI[sha256sum] = "d02c1b2d927aebbc761711bddc23c05a64e8fd62a5d733b732cc0a58cca52f06"

SRC_URI = "https://${PBT_ARTIFACTORY}/${PBT_BUILD_ID}/${PBT_BIN_PATH}/${BPN}_${PV}_${PBT_ARCH}.tar.gz"

PACKAGE_ARCH = "${MACHINE_ARCH}"

FILES:${PN} += "/usr/lib/* ${libdir}/* "


SOLIBS = ".so"
FILES_SOLIBSDEV = ""
