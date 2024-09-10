inherit qprebuilt pkgconfig

LICENSE          = "Qualcomm-Technologies-Inc.-Proprietary"
LIC_FILES_CHKSUM = "file://${QCOM_COMMON_LICENSE_DIR}${LICENSE};md5=58d50a3d36f27f1a1e6089308a49b403"

DESCRIPTION = "sva-aml"

DEPENDS += "qcom-sva-common qcom-sva-ml-commondwarf2"

PBT_ARCH = "armv8-2a"

SRC_URI[armv8-2a.sha256sum] = "1007dbe0b6da2e00524ee9a036510bb7722d547edb51c1f4784be79f2002bb6e"

SRC_URI = "https://${PBT_ARTIFACTORY}/${PBT_BUILD_ID}/${PBT_BIN_PATH}/${BPN}_${PV}_${PBT_ARCH}.tar.gz;name=${PBT_ARCH}"
