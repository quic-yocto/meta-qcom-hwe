inherit qprebuilt pkgconfig

LICENSE          = "Qualcomm-Technologies-Inc.-Proprietary"
LIC_FILES_CHKSUM = "file://${QCOM_COMMON_LICENSE_DIR}${LICENSE};md5=58d50a3d36f27f1a1e6089308a49b403"

DESCRIPTION = "Qualcomm Atheros ath6kl utils."

DEPENDS += "diag libnl glib-2.0"

PV = "1.0"

SRC_URI[qcm6490.sha256sum] = "3c0265b109d787b9d810bbfbea128b46242616987242bbed43fb7943d7c5ee25"
SRC_URI[qcs9100.sha256sum] = "2746a8823c82ba912850fa5dc2f0e9ac82726629730483f05946ecc6d62fa3c1"

SRC_URI = "https://${PBT_ARTIFACTORY}/${PBT_BUILD_ID}/${PBT_BIN_PATH}/${BPN}_${PV}_${PBT_ARCH}.tar.gz;name=${PBT_ARCH}"

PACKAGE_ARCH = "${MACHINE_ARCH}"
