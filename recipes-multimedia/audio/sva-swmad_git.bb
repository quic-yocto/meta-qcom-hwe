inherit qprebuilt pkgconfig

LICENSE          = "Qualcomm-Technologies-Inc.-Proprietary"
LIC_FILES_CHKSUM = "file://${QCOM_COMMON_LICENSE_DIR}${LICENSE};md5=58d50a3d36f27f1a1e6089308a49b403"

DESCRIPTION = "sva-cnn"

DEPENDS += "sva-common sva-listen-common"

PBT_ARCH = "armv8-2a"

SRC_URI[armv8-2a.sha256sum] = "451d1dc9721f2caacb6db3dc3b74a0e85f21b6eca35c62dffe9446b9030cda45"

SRC_URI = "https://${PBT_ARTIFACTORY}/${PBT_BUILD_ID}/${PBT_BIN_PATH}/${BPN}_${PV}_${PBT_ARCH}.tar.gz;name=${PBT_ARCH}"
