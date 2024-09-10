inherit qprebuilt pkgconfig

LICENSE          = "Qualcomm-Technologies-Inc.-Proprietary"
LIC_FILES_CHKSUM = "file://${QCOM_COMMON_LICENSE_DIR}${LICENSE};md5=58d50a3d36f27f1a1e6089308a49b403"

DESCRIPTION = "sva-xs"

DEPENDS += "qcom-sva-common qcom-sva-uv qcom-capiv2-headers qcom-sva-listen-common qcom-sva-gmm qcom-sva-swmad qcom-sva-eai-utils qcom-sva-eai"

PBT_ARCH = "armv8-2a"

SRC_URI[armv8-2a.sha256sum] = "43aa22e9096e56c54dd5176761a20c0632bae039473dee3b729098994535a27a"

SRC_URI = "https://${PBT_ARTIFACTORY}/${PBT_BUILD_ID}/${PBT_BIN_PATH}/${BPN}_${PV}_${PBT_ARCH}.tar.gz;name=${PBT_ARCH}"

SOLIBS = ".so"
FILES_SOLIBSDEV = ""
