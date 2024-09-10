inherit qprebuilt pkgconfig

LICENSE          = "Qualcomm-Technologies-Inc.-Proprietary"
LIC_FILES_CHKSUM = "file://${QCOM_COMMON_LICENSE_DIR}${LICENSE};md5=58d50a3d36f27f1a1e6089308a49b403"

DESCRIPTION = "eai-utils"

DEPENDS += "qcom-sva-eai-utils"

PBT_ARCH = "armv8-2a"

SRC_URI[armv8-2a.sha256sum] = "c0a02830b108806bc5d838e30885b451c5138222fdf5f602ed42ef17fc998dd2"

SRC_URI = "https://${PBT_ARTIFACTORY}/${PBT_BUILD_ID}/${PBT_BIN_PATH}/${BPN}_${PV}_${PBT_ARCH}.tar.gz;name=${PBT_ARCH}"
