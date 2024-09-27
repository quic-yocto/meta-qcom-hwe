inherit qprebuilt pkgconfig

LICENSE          = "Qualcomm-Technologies-Inc.-Proprietary"
LIC_FILES_CHKSUM = "file://${QCOM_COMMON_LICENSE_DIR}${LICENSE};md5=58d50a3d36f27f1a1e6089308a49b403"

DESCRIPTION = "sva-kwd_enroll"

DEPENDS += "qcom-sva-ml-commondwarf2 qcom-sva-common qcom-sva-aml"

PBT_ARCH = "armv8-2a"

SRC_URI[armv8-2a.sha256sum] = "13978d35326c4f10abccbc77a787cd7ac2b2fdb29fda133b94363a2a17449a9f"

SRC_URI = "https://${PBT_ARTIFACTORY}/${PBT_BUILD_ID}/${PBT_BIN_PATH}/${BPN}_${PV}_${PBT_ARCH}.tar.gz;name=${PBT_ARCH}"
