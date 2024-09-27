inherit qprebuilt pkgconfig

LICENSE          = "Qualcomm-Technologies-Inc.-Proprietary"
LIC_FILES_CHKSUM = "file://${QCOM_COMMON_LICENSE_DIR}${LICENSE};md5=58d50a3d36f27f1a1e6089308a49b403"

DESCRIPTION = "eai-utils"

DEPENDS += "qcom-sva-eai-utils"

PBT_ARCH = "armv8-2a"

SRC_URI[armv8-2a.sha256sum] = "c369736a05115be34a2383ab501d7f540d419515bf16c6e95c57bc84d168e6d7"

SRC_URI = "https://${PBT_ARTIFACTORY}/${PBT_BUILD_ID}/${PBT_BIN_PATH}/${BPN}_${PV}_${PBT_ARCH}.tar.gz;name=${PBT_ARCH}"
