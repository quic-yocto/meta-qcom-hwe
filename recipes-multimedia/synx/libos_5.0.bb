inherit qprebuilt pkgconfig systemd

LICENSE          = "Qualcomm-Technologies-Inc.-Proprietary"
LIC_FILES_CHKSUM = "file://${QCOM_COMMON_LICENSE_DIR}${LICENSE};md5=58d50a3d36f27f1a1e6089308a49b403"

DESCRIPTION = "Generates synx os lib"

DEPENDS += "glib-2.0"

SRC_URI[sha256sum] = "7c62c6cf36e883e47502518a36f2f803a2222ee4e37927ec79efb7a62582bb1a"

SRC_URI = "https://${PBT_ARTIFACTORY}/${PBT_BUILD_ID}/${PBT_BIN_PATH}/${BPN}_${PV}_${PBT_ARCH}.tar.gz"

PACKAGE_ARCH = "${MACHINE_ARCH}"

FILES:${PN} += "/usr/lib/* ${libdir}/* "


INSANE_SKIP:${PN} += "arch"
INSANE_SKIP:${PN} += "already-stripped"


SOLIBS = ".so"
FILES_SOLIBSDEV = ""
