inherit qprebuilt pkgconfig

LICENSE          = "Qualcomm-Technologies-Inc.-Proprietary"
LIC_FILES_CHKSUM = "file://${QCOM_COMMON_LICENSE_DIR}${LICENSE};md5=58d50a3d36f27f1a1e6089308a49b403"

DESCRIPTION = "eai-utils"

DEPENDS += "sva-eai-utils"

PBT_ARCH = "armv8-2a"

SRC_URI[sha256sum] = "598d061902f819402a40bf9e5dfd967f6a9667841f6ced65351b19f0d3a862db"

SRC_URI = "https://${PBT_ARTIFACTORY}/${PBT_BUILD_ID}/${PBT_BIN_PATH}/${BPN}_${PV}_${PBT_ARCH}.tar.gz"
