inherit qprebuilt pkgconfig

LICENSE          = "Qualcomm-Technologies-Inc.-Proprietary"
LIC_FILES_CHKSUM = "file://${QCOM_COMMON_LICENSE_DIR}${LICENSE};md5=58d50a3d36f27f1a1e6089308a49b403"

DESCRIPTION = "sva-gmm"

DEPENDS += "qcom-sva-common qcom-sva-listen-common qcom-sva-swmad"

PBT_ARCH = "armv8-2a"

SRC_URI[armv8-2a.sha256sum] = "b19bc7223298049be172b47460d2ce8ff7e38e941b91531fca3044ff0a025859"

SRC_URI = "https://${PBT_ARTIFACTORY}/${PBT_BUILD_ID}/${PBT_BIN_PATH}/${BPN}_${PV}_${PBT_ARCH}.tar.gz;name=${PBT_ARCH}"
