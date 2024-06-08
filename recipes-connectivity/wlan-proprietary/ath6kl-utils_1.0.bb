inherit qprebuilt pkgconfig

LICENSE          = "Qualcomm-Technologies-Inc.-Proprietary"
LIC_FILES_CHKSUM = "file://${QCOM_COMMON_LICENSE_DIR}${LICENSE};md5=58d50a3d36f27f1a1e6089308a49b403"

DESCRIPTION = "Qualcomm Atheros ath6kl utils."

DEPENDS += "diag libnl glib-2.0"
PV = "1.0"

SRC_URI[sha256sum] = "f18cb5ddd0b890af696e9660bc0f49756a5ffc12df240bc2770b19adcf143f27"

SRC_URI = "https://${PBT_ARTIFACTORY}/${PBT_BUILD_ID}/${PBT_BIN_PATH}/${BPN}_${PV}_${PBT_ARCH}.tar.gz;name=${PBT_ARCH}"

PACKAGE_ARCH = "${MACHINE_ARCH}"
