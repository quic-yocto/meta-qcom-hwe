inherit qprebuilt pkgconfig

LICENSE          = "Qualcomm-Technologies-Inc.-Proprietary"
LIC_FILES_CHKSUM = "file://${QCOM_COMMON_LICENSE_DIR}${LICENSE};md5=58d50a3d36f27f1a1e6089308a49b403"

DESCRIPTION = "Camx"

DEPENDS += "syslog-plumber glib-2.0 gbm property-vault fastrpc adreno"

RDEPENDS:${PN} += "cameradlkm"

SRC_URI[qcm6490.sha256sum] = "61a93bec84dde428cff3c734fcdfda9f604215d7f7a8b635a9a0970c4b5b5fce"
SRC_URI[qcs9100.sha256sum] = "32d2b5c3d8d1f873cb364c67f295acbaf795cb7c83f24ebf88ad9a44ebac3d59"

SRC_URI = "https://${PBT_ARTIFACTORY}/${PBT_BUILD_ID}/${PBT_BIN_PATH}/${BPN}_${PV}_${PBT_ARCH}.tar.gz;name=${PBT_ARCH}"

FILES:${PN} = "\
    /usr/lib/* \
    /usr/bin/* \
    /usr/include/* \
    /lib/firmware/*"
FILES:${PN}-dev = ""



INSANE_SKIP:${PN}-dbg = "arch"
INSANE_SKIP:${PN} = "arch"
