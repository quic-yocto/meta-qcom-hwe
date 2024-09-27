inherit qprebuilt pkgconfig

LICENSE          = "Qualcomm-Technologies-Inc.-Proprietary"
LIC_FILES_CHKSUM = "file://${QCOM_COMMON_LICENSE_DIR}${LICENSE};md5=58d50a3d36f27f1a1e6089308a49b403"

DESCRIPTION = "sva-xs"

DEPENDS += "qcom-sva-common qcom-sva-eai-utils qcom-sva-eai qcom-sva-listen-common"

PBT_ARCH = "armv8-2a"

SRC_URI[armv8-2a.sha256sum] = "b372d9dca4a23a7e7d3e0e1b1a002d23e94b57b8a67e8a4c80bd9cfb752129f0"

SRC_URI = "https://${PBT_ARTIFACTORY}/${PBT_BUILD_ID}/${PBT_BIN_PATH}/${BPN}_${PV}_${PBT_ARCH}.tar.gz;name=${PBT_ARCH}"
