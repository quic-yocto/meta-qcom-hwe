inherit qprebuilt pkgconfig

LICENSE          = "Qualcomm-Technologies-Inc.-Proprietary"
LIC_FILES_CHKSUM = "file://${QCOM_COMMON_LICENSE_DIR}${LICENSE};md5=58d50a3d36f27f1a1e6089308a49b403"

DESCRIPTION = "Securemsm headers"

SRC_URI[qcm6490.sha256sum] = "0f5b41f5ea18b7214071b8f5f18fbf487fbb5a5b64d0c41e45718cf4b058765d"
SRC_URI[qcs9100.sha256sum] = "dfe67ee9cc9124a0b75f3808a89b05e55846e03c35562d476ff81e31497d4969"

SRC_URI = "https://${PBT_ARTIFACTORY}/${PBT_BUILD_ID}/${PBT_BIN_PATH}/${BPN}_${PV}_${PBT_ARCH}.tar.gz;name=${PBT_ARCH}"

PACKAGE_ARCH = "${MACHINE_ARCH}"

ALLOW_EMPTY:${PN} = "1"
