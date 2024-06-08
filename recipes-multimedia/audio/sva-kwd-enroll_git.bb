inherit qprebuilt pkgconfig

LICENSE          = "Qualcomm-Technologies-Inc.-Proprietary"
LIC_FILES_CHKSUM = "file://${QCOM_COMMON_LICENSE_DIR}${LICENSE};md5=58d50a3d36f27f1a1e6089308a49b403"

DESCRIPTION = "sva-kwd_enroll"

DEPENDS += "sva-ml-commondwarf2 sva-common sva-aml"

PBT_ARCH = "armv8-2a"

SRC_URI[armv8-2a.sha256sum] = "8edf9885b6582538731249014904a5a8985f14cf16af86a49f5ca652d6bd7d15"

SRC_URI = "https://${PBT_ARTIFACTORY}/${PBT_BUILD_ID}/${PBT_BIN_PATH}/${BPN}_${PV}_${PBT_ARCH}.tar.gz;name=${PBT_ARCH}"
