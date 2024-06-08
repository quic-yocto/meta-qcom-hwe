inherit qprebuilt pkgconfig

LICENSE          = "Qualcomm-Technologies-Inc.-Proprietary"
LIC_FILES_CHKSUM = "file://${QCOM_COMMON_LICENSE_DIR}${LICENSE};md5=58d50a3d36f27f1a1e6089308a49b403"

DESCRIPTION = "sva-aml"

DEPENDS += "sva-common sva-ml-commondwarf2"

PBT_ARCH = "armv8-2a"

SRC_URI[armv8-2a.sha256sum] = "c50dcbac6277e5675425d5b4ce6ce4e35c2fd264fabe0c32081aa083ee83b953"

SRC_URI = "https://${PBT_ARTIFACTORY}/${PBT_BUILD_ID}/${PBT_BIN_PATH}/${BPN}_${PV}_${PBT_ARCH}.tar.gz;name=${PBT_ARCH}"
