inherit qprebuilt

LICENSE          = "Qualcomm-Technologies-Inc.-Proprietary"
LIC_FILES_CHKSUM = "file://${QCOM_COMMON_LICENSE_DIR}${LICENSE};md5=58d50a3d36f27f1a1e6089308a49b403"

DESCRIPTION = "Example for Secure DSP usecase."

DEPENDS += "fastrpc libvmmem securemsm-features"

SRC_URI[sha256sum] = "3dcfb97a5aa8aa36be0b65069298b43ec120da511df636d73fca0a20fbd4ce4d"

SRC_URI = "https://${PBT_ARTIFACTORY}/${PBT_BUILD_ID}/${PBT_BIN_PATH}/${BPN}_${PV}_${PBT_ARCH}.tar.gz"

PACKAGE_ARCH = "${MACHINE_ARCH}"

FILES:${PN} = "${libdir}/*.so ${bindir}/*"
FILES:${PN}-dev = "${libdir}/*.la ${includedir}"
