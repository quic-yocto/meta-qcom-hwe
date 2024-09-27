inherit qprebuilt pkgconfig

LICENSE          = "Qualcomm-Technologies-Inc.-Proprietary"
LIC_FILES_CHKSUM = "file://${QCOM_COMMON_LICENSE_DIR}${LICENSE};md5=58d50a3d36f27f1a1e6089308a49b403"

DESCRIPTION = "sva-ml-common"

DEPENDS += "qcom-sva-common"

PBT_ARCH = "armv8-2a"

SRC_URI[armv8-2a.sha256sum] = "1a150c2723e130ccf88be83ce3fd1052de208a9826381dd9db8a5e7339df2d5d"

SRC_URI = "https://${PBT_ARTIFACTORY}/${PBT_BUILD_ID}/${PBT_BIN_PATH}/${BPN}_${PV}_${PBT_ARCH}.tar.gz;name=${PBT_ARCH}"
