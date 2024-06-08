inherit qprebuilt pkgconfig

LICENSE          = "Qualcomm-Technologies-Inc.-Proprietary"
LIC_FILES_CHKSUM = "file://${QCOM_COMMON_LICENSE_DIR}${LICENSE};md5=58d50a3d36f27f1a1e6089308a49b403"

DESCRIPTION = "sva-xs"

DEPENDS += "sva-common sva-uv capiv2-headers sva-listen-common sva-gmm sva-swmad sva-eai-utils sva-eai"

PBT_ARCH = "armv8-2a"

SRC_URI[sha256sum] = "6fd72499737395e251d12874e449490eb882e605272d26e63c08d9137976a720"

SRC_URI = "https://${PBT_ARTIFACTORY}/${PBT_BUILD_ID}/${PBT_BIN_PATH}/${BPN}_${PV}_${PBT_ARCH}.tar.gz;name=${PBT_ARCH}"

SOLIBS = ".so"
FILES_SOLIBSDEV = ""
