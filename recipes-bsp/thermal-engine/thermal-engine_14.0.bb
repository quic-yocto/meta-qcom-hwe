inherit qprebuilt pkgconfig systemd

LICENSE          = "Qualcomm-Technologies-Inc.-Proprietary"
LIC_FILES_CHKSUM = "file://${QCOM_COMMON_LICENSE_DIR}${LICENSE};md5=58d50a3d36f27f1a1e6089308a49b403"

DESCRIPTION = "Thermal Engine"

DEPENDS += "qmi-framework glib-2.0 libnl linux-kernel-qcom-headers"

QCM6490_SHA256SUM = "da81a5f4bc0d9218dc6d15e1fa84b5662bb756a08368440f740a6fd8ce11b0d2"
QCS9100_SHA256SUM = "dfb005dfed0b73c8759c1553bc6ebbc6030f4cb9408bfee9aadc99a13c936fa1"
QCS8300_SHA256SUM = "fe1798c6ae3ed9d2a4f9f8d266f114de3f7070d712fb2c88b0dfb4a8af3e1bab"

SRC_URI[qcm6490.sha256sum] = "${QCM6490_SHA256SUM}"
SRC_URI[qcs9100.sha256sum] = "${QCS9100_SHA256SUM}"
SRC_URI[qcs8300.sha256sum] = "${QCS8300_SHA256SUM}"

SRC_URI = "https://${PBT_ARTIFACTORY}/${PBT_BUILD_ID}/${PBT_BIN_PATH}/${BPN}_${PV}_${PBT_ARCH}.tar.gz;name=${PBT_ARCH}"
