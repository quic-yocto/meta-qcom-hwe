inherit qprebuilt pkgconfig

LICENSE          = "Qualcomm-Technologies-Inc.-Proprietary"
LIC_FILES_CHKSUM = "file://${QCOM_COMMON_LICENSE_DIR}${LICENSE};md5=58d50a3d36f27f1a1e6089308a49b403"

DESCRIPTION = "sva-gmm"

DEPENDS += "sva-common sva-listen-common sva-swmad"

PBT_ARCH = "armv8-2a"

SRC_URI[armv8-2a.sha256sum] = "cab05a27332d799bd37561431108da5f6a623d0270ed38075fffcc2519384f47"

SRC_URI = "https://${PBT_ARTIFACTORY}/${PBT_BUILD_ID}/${PBT_BIN_PATH}/${BPN}_${PV}_${PBT_ARCH}.tar.gz;name=${PBT_ARCH}"
