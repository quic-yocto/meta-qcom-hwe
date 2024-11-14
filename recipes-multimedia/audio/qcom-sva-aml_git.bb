inherit qprebuilt pkgconfig

LICENSE          = "Qualcomm-Technologies-Inc.-Proprietary"
LIC_FILES_CHKSUM = "file://${QCOM_COMMON_LICENSE_DIR}${LICENSE};md5=58d50a3d36f27f1a1e6089308a49b403"

DESCRIPTION = "sva-aml"

DEPENDS += "qcom-sva-common qcom-sva-ml-commondwarf2"

PBT_ARCH = "armv8-2a"

ARMV8_SHA256SUM = "fa2fd1ef5e9492eb42885eee692a03dd37e9d5139f5f5c55923422c8480c30a0"

SRC_URI[armv8-2a.sha256sum] = "${ARMV8_SHA256SUM}"

SRC_URI = "https://${PBT_ARTIFACTORY}/${PBT_BUILD_ID}/${PBT_BIN_PATH}/${BPN}_${PV}_${PBT_ARCH}.tar.gz;name=${PBT_ARCH}"
