inherit qprebuilt pkgconfig

LICENSE          = "Qualcomm-Technologies-Inc.-Proprietary"
LIC_FILES_CHKSUM = "file://${QCOM_COMMON_LICENSE_DIR}${LICENSE};md5=58d50a3d36f27f1a1e6089308a49b403"

DESCRIPTION = "sva-ml-common"

DEPENDS += "sva-common"

PBT_ARCH = "armv8-2a"

SRC_URI[sha256sum] = "a8d29f06e243084963e028b79f749ae5ee90e55cc31e9d8f21ddd381c71823c7"

SRC_URI = "https://${PBT_ARTIFACTORY}/${PBT_BUILD_ID}/${PBT_BIN_PATH}/${BPN}_${PV}_${PBT_ARCH}.tar.gz;name=${PBT_ARCH}"

SOLIBS = ".so"
FILES_SOLIBSDEV = ""
