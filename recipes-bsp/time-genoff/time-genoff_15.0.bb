inherit qprebuilt pkgconfig

LICENSE          = "Qualcomm-Technologies-Inc.-Proprietary"
LIC_FILES_CHKSUM = "file://${QCOM_COMMON_LICENSE_DIR}${LICENSE};md5=58d50a3d36f27f1a1e6089308a49b403"

DESCRIPTION = "Time Genoff Library"

SRC_URI[qcm6490.sha256sum] = "af5577833840938562720086ff25c483980ac95dd7497be3559bd9fef9ac29e0"
SRC_URI[qcs9100.sha256sum] = "a04b929479323d9cfa0a3616066b276ce8ecec39aef3b06bc2925d6716260967"
SRC_URI[qcs8300.sha256sum] = "7db3734c00a0a8bf9707d57cf24d451937701f921f4cba2071ac6d33f71d3302"

SRC_URI = "https://${PBT_ARTIFACTORY}/${PBT_BUILD_ID}/${PBT_BIN_PATH}/${BPN}_${PV}_${PBT_ARCH}.tar.gz;name=${PBT_ARCH}"
