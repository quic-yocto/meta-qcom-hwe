inherit qprebuilt pkgconfig

LICENSE          = "Qualcomm-Technologies-Inc.-Proprietary"
LIC_FILES_CHKSUM = "file://${QCOM_COMMON_LICENSE_DIR}${LICENSE};md5=58d50a3d36f27f1a1e6089308a49b403"

DESCRIPTION = "Time Genoff Library"

SRC_URI[qcm6490.sha256sum] = "b78d09fb75dea2a198826ddbf66c405ffbc4a42d29ac087ea9a7ee8a4ac463f6"
SRC_URI[qcs9100.sha256sum] = "ea5342014e43bab7941351a785a959b5391adfc849294f32b8600c3c9330f78e"

SRC_URI = "https://${PBT_ARTIFACTORY}/${PBT_BUILD_ID}/${PBT_BIN_PATH}/${BPN}_${PV}_${PBT_ARCH}.tar.gz;name=${PBT_ARCH}"

PACKAGE_ARCH = "${MACHINE_ARCH}"
