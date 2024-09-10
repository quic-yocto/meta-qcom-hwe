inherit qprebuilt pkgconfig

LICENSE          = "Qualcomm-Technologies-Inc.-Proprietary"
LIC_FILES_CHKSUM = "file://${QCOM_COMMON_LICENSE_DIR}${LICENSE};md5=58d50a3d36f27f1a1e6089308a49b403"

DESCRIPTION = "sva-aml"

DEPENDS += "qcom-sva-common qcom-sva-ml-commondwarf2"

PBT_ARCH = "armv8-2a"

SRC_URI[armv8-2a.sha256sum] = "3e006399e23707bac03610d4aa3dded033c8cb959f62998e67c08325d6e68fba"

SRC_URI = "https://${PBT_ARTIFACTORY}/${PBT_BUILD_ID}/${PBT_BIN_PATH}/${BPN}_${PV}_${PBT_ARCH}.tar.gz;name=${PBT_ARCH}"
