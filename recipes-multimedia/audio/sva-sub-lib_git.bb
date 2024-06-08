inherit qprebuilt pkgconfig

LICENSE          = "Qualcomm-Technologies-Inc.-Proprietary"
LIC_FILES_CHKSUM = "file://${QCOM_COMMON_LICENSE_DIR}${LICENSE};md5=58d50a3d36f27f1a1e6089308a49b403"

DESCRIPTION = "eai-utils"

DEPENDS += "sva-eai-utils"

PBT_ARCH = "armv8-2a"

SRC_URI[sha256sum] = "6bb2b7c672ddae02af028cf6efb30fa8eb84800e98a80cf765ad4e2ab95fb8ba"

SRC_URI = "https://${PBT_ARTIFACTORY}/${PBT_BUILD_ID}/${PBT_BIN_PATH}/${BPN}_${PV}_${PBT_ARCH}.tar.gz;name=${PBT_ARCH}"
