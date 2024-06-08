inherit qprebuilt

LICENSE          = "Qualcomm-Technologies-Inc.-Proprietary"
LIC_FILES_CHKSUM = "file://${QCOM_COMMON_LICENSE_DIR}${LICENSE};md5=58d50a3d36f27f1a1e6089308a49b403"

DESCRIPTION = "dspservices headers."

SRC_URI[qcm6490.sha256sum] = "281cac399b12290d8c5430383e4d9d16365c9c5906a4009e360f1025ddd5a945"
SRC_URI[qcs9100.sha256sum] = "5714bb65a8f77ef0eb75dd01a90b9571c9c2534cd2d23b90e132bbfb1f52b78e"

SRC_URI = "https://${PBT_ARTIFACTORY}/${PBT_BUILD_ID}/${PBT_BIN_PATH}/${BPN}_${PV}_${PBT_ARCH}.tar.gz;name=${PBT_ARCH}"

PACKAGE_ARCH = "${MACHINE_ARCH}"

ALLOW_EMPTY:${PN} = "1"
