inherit qprebuilt

LICENSE          = "Qualcomm-Technologies-Inc.-Proprietary"
LIC_FILES_CHKSUM = "file://${QCOM_COMMON_LICENSE_DIR}${LICENSE};md5=58d50a3d36f27f1a1e6089308a49b403"

DESCRIPTION = "Recipe to install video firmware files on rootfs"

SRC_URI[qcm6490.sha256sum] = "e2198fa1f1c9fb99aa629bef3fa12f8dd67d66c0702b4f758adece2d3ac5bbbb"
SRC_URI[qcs9100.sha256sum] = "5b64fe1f2dc4b70e6a62f4985f153a801b4e3558290c62bd3cdeffa7ba4547ab"

SRC_URI = "https://${PBT_ARTIFACTORY}/${PBT_BUILD_ID}/${PBT_BIN_PATH}/${BPN}_${PV}_${PBT_ARCH}.tar.gz;name=${PBT_ARCH}"

PACKAGE_ARCH = "${MACHINE_ARCH}"

FILES:${PN} += "/lib/firmware"


INSANE_SKIP:${PN} = "arch"


INHIBIT_PACKAGE_DEBUG_SPLIT = "1"
