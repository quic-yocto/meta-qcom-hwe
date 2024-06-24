inherit qprebuilt

LICENSE          = "Qualcomm-Technologies-Inc.-Proprietary"
LIC_FILES_CHKSUM = "file://${QCOM_COMMON_LICENSE_DIR}${LICENSE};md5=58d50a3d36f27f1a1e6089308a49b403"

DESCRIPTION = "dspservices headers."

SRC_URI[qcm6490.sha256sum] = "ff54286e1442034fb10f0b4ac485972fc69855c02dc05185ea633223aba2d7dc"
SRC_URI[qcs9100.sha256sum] = "7dab80752c54dabe7bcf4de7138e08399a5537d9059cac0309fad78aa12ca436"

SRC_URI = "https://${PBT_ARTIFACTORY}/${PBT_BUILD_ID}/${PBT_BIN_PATH}/${BPN}_${PV}_${PBT_ARCH}.tar.gz;name=${PBT_ARCH}"

PACKAGE_ARCH = "${MACHINE_ARCH}"

ALLOW_EMPTY:${PN} = "1"
