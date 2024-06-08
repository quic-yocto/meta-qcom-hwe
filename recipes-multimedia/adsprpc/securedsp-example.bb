inherit qprebuilt

LICENSE          = "Qualcomm-Technologies-Inc.-Proprietary"
LIC_FILES_CHKSUM = "file://${QCOM_COMMON_LICENSE_DIR}${LICENSE};md5=58d50a3d36f27f1a1e6089308a49b403"

DESCRIPTION = "Example for Secure DSP usecase."

DEPENDS += "fastrpc libvmmem securemsm-features"

SRC_URI[sha256sum] = "7fb17d1efd26cbf49c5854ee3ba5bebc204f51f0e0163c18adb4eea8a1496287"

SRC_URI = "https://${PBT_ARTIFACTORY}/${PBT_BUILD_ID}/${PBT_BIN_PATH}/${BPN}_${PV}_${PBT_ARCH}.tar.gz;name=${PBT_ARCH}"

PACKAGE_ARCH = "${MACHINE_ARCH}"

FILES:${PN} = "${libdir}/*.so ${bindir}/*"
FILES:${PN}-dev = "${libdir}/*.la ${includedir}"
