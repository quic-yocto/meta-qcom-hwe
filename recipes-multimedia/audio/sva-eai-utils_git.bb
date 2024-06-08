inherit qprebuilt pkgconfig

LICENSE          = "Qualcomm-Technologies-Inc.-Proprietary"
LIC_FILES_CHKSUM = "file://${QCOM_COMMON_LICENSE_DIR}${LICENSE};md5=58d50a3d36f27f1a1e6089308a49b403"

DESCRIPTION = "eai-utils"

DEPENDS += "sva-eai"

PBT_ARCH = "armv8-2a"

SRC_URI[armv8-2a.sha256sum] = "8cfa3cafb0aef8f3f5362a967978702bc4329d0bcc22fc43baf389097a0acf36"

SRC_URI = "https://${PBT_ARTIFACTORY}/${PBT_BUILD_ID}/${PBT_BIN_PATH}/${BPN}_${PV}_${PBT_ARCH}.tar.gz;name=${PBT_ARCH}"
