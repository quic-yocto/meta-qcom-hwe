inherit qprebuilt pkgconfig

LICENSE          = "Qualcomm-Technologies-Inc.-Proprietary"
LIC_FILES_CHKSUM = "file://${QCOM_COMMON_LICENSE_DIR}${LICENSE};md5=58d50a3d36f27f1a1e6089308a49b403"

DESCRIPTION = "sva-statereorder"

DEPENDS += "qcom-sva-listen-sound-model-headers qcom-sva-common qcom-sva-listen-common"

PBT_ARCH = "armv8-2a"

SRC_URI[armv8-2a.sha256sum] = "56fff112b6209a162ff2be5cda0fe39e760f714da59c66fc01c8d9127df2d1d3"

SRC_URI = "https://${PBT_ARTIFACTORY}/${PBT_BUILD_ID}/${PBT_BIN_PATH}/${BPN}_${PV}_${PBT_ARCH}.tar.gz;name=${PBT_ARCH}"
