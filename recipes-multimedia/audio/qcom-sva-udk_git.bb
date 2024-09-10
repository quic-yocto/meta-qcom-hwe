inherit qprebuilt pkgconfig

LICENSE          = "Qualcomm-Technologies-Inc.-Proprietary"
LIC_FILES_CHKSUM = "file://${QCOM_COMMON_LICENSE_DIR}${LICENSE};md5=58d50a3d36f27f1a1e6089308a49b403"

DESCRIPTION = "sva-uv"

DEPENDS += "qcom-sva-common qcom-sva-eai-utils qcom-sva-eai qcom-vui-interface-header"

PBT_ARCH = "armv8-2a"

SRC_URI[armv8-2a.sha256sum] = "aead2289488b0b3e0dea3496bb0928a394792cc3107f459e111cd7da5f356d6c"

SRC_URI = "https://${PBT_ARTIFACTORY}/${PBT_BUILD_ID}/${PBT_BIN_PATH}/${BPN}_${PV}_${PBT_ARCH}.tar.gz;name=${PBT_ARCH}"
