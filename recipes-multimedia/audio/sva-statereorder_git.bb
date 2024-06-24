inherit qprebuilt pkgconfig

LICENSE          = "Qualcomm-Technologies-Inc.-Proprietary"
LIC_FILES_CHKSUM = "file://${QCOM_COMMON_LICENSE_DIR}${LICENSE};md5=58d50a3d36f27f1a1e6089308a49b403"

DESCRIPTION = "sva-statereorder"

DEPENDS += "sva-listen-sound-model-headers sva-common sva-listen-common"

PBT_ARCH = "armv8-2a"

SRC_URI[armv8-2a.sha256sum] = "4772c58397b2b213325884393971628f5e69c1cdb2c68f492e884eac8b62b364"

SRC_URI = "https://${PBT_ARTIFACTORY}/${PBT_BUILD_ID}/${PBT_BIN_PATH}/${BPN}_${PV}_${PBT_ARCH}.tar.gz;name=${PBT_ARCH}"
