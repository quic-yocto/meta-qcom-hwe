inherit qprebuilt pkgconfig systemd

LICENSE          = "Qualcomm-Technologies-Inc.-Proprietary"
LIC_FILES_CHKSUM = "file://${QCOM_COMMON_LICENSE_DIR}${LICENSE};md5=58d50a3d36f27f1a1e6089308a49b403"

DESCRIPTION = "Generates synx libs"

DEPENDS += "libos libthreadutils synx-kernel-header"

PBT_ARCH = "armv8-2a"

SRC_URI[sha256sum] = "603bfd7f449d44820ee9852c4f034c1080ce9d109881e0d7985c245f61d65e3a"

SRC_URI = "https://${PBT_ARTIFACTORY}/${PBT_BUILD_ID}/${PBT_BIN_PATH}/${BPN}_${PV}_${PBT_ARCH}.tar.gz"

FILES:${PN} += "/usr/lib/* ${libdir}/* "


INSANE_SKIP:${PN} += "arch"
INSANE_SKIP:${PN} += "already-stripped"


SOLIBS = ".so"
FILES_SOLIBSDEV = ""
