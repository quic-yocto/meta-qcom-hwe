inherit qprebuilt pkgconfig

LICENSE          = "Qualcomm-Technologies-Inc.-Proprietary"
LIC_FILES_CHKSUM = "file://${QCOM_COMMON_LICENSE_DIR}${LICENSE};md5=58d50a3d36f27f1a1e6089308a49b403"

DESCRIPTION = "Securemsm headers"

SRC_URI[qcm6490.sha256sum] = "e8db29ce0428e130591c6f651492316f9ac533f0e0e28e0ee9014359e2c6bd8c"
SRC_URI[qcs9100.sha256sum] = "07a5446557ce95312942dff26aa320af9bb69202349d23e200961d686b252fce"
SRC_URI[qcs8300.sha256sum] = "c80ece5d30734ab416b77e4e4c47714c81291a4b374f213156718e8cc8d48f51"

SRC_URI = "https://${PBT_ARTIFACTORY}/${PBT_BUILD_ID}/${PBT_BIN_PATH}/${BPN}_${PV}_${PBT_ARCH}.tar.gz;name=${PBT_ARCH}"

ALLOW_EMPTY:${PN} = "1"
