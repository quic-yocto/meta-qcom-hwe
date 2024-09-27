inherit qprebuilt pkgconfig

LICENSE          = "Qualcomm-Technologies-Inc.-Proprietary"
LIC_FILES_CHKSUM = "file://${QCOM_COMMON_LICENSE_DIR}${LICENSE};md5=58d50a3d36f27f1a1e6089308a49b403"

DESCRIPTION = "Securemsm headers"

SRC_URI[qcm6490.sha256sum] = "9d4212b22adb31765470736169f5c005ee32ae7d0f8ec52cbdd92137d8fddd17"
SRC_URI[qcs9100.sha256sum] = "a034a41c1a740aeac7d34f5221e13806830bb1dbc466ff6614c83a6934a41221"
SRC_URI[qcs8300.sha256sum] = "737569a27099a1382aa024c41fe8344d8c893ae773aaac6afb6872e8dd85fc50"

SRC_URI = "https://${PBT_ARTIFACTORY}/${PBT_BUILD_ID}/${PBT_BIN_PATH}/${BPN}_${PV}_${PBT_ARCH}.tar.gz;name=${PBT_ARCH}"

ALLOW_EMPTY:${PN} = "1"
