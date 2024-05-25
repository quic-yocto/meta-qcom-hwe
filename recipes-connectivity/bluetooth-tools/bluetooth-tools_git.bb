inherit qprebuilt

LICENSE          = "Qualcomm-Technologies-Inc.-Proprietary"
LIC_FILES_CHKSUM = "file://${QCOM_COMMON_LICENSE_DIR}${LICENSE};md5=58d50a3d36f27f1a1e6089308a49b403"

DESCRIPTION = "Bluetooth tools layer"

PBT_ARCH = "armv8-2a"

SRC_URI[sha256sum] = "ff53329499d83746e62bcfd627b1e42f3ea1b66a4cf3aa523483fcf8e7255f29"

SRC_URI = "https://${PBT_ARTIFACTORY}/${PBT_BUILD_ID}/${PBT_BIN_PATH}/${BPN}_${PV}_${PBT_ARCH}.tar.gz"

INSANE_SKIP:${PN} += "file-rdeps"

