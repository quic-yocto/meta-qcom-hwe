inherit qprebuilt pkgconfig

LICENSE          = "Qualcomm-Technologies-Inc.-Proprietary"
LIC_FILES_CHKSUM = "file://${QCOM_COMMON_LICENSE_DIR}${LICENSE};md5=58d50a3d36f27f1a1e6089308a49b403"

DESCRIPTION = "sva-uv"

DEPENDS += "sva-sub-lib sva-common sva-eai-utils sva-eai sva-listen-common sva-gmm sva-swmad sva-listen-sound-model-headers"

PBT_ARCH = "armv8-2a"

SRC_URI[armv8-2a.sha256sum] = "f27ecf2d2ebfd65679aebf3690dc58ed44236c791cc37562096399c9a392ea26"

SRC_URI = "https://${PBT_ARTIFACTORY}/${PBT_BUILD_ID}/${PBT_BIN_PATH}/${BPN}_${PV}_${PBT_ARCH}.tar.gz;name=${PBT_ARCH}"
