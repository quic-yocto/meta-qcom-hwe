inherit qprebuilt pkgconfig

LICENSE          = "Qualcomm-Technologies-Inc.-Proprietary"
LIC_FILES_CHKSUM = "file://${QCOM_COMMON_LICENSE_DIR}${LICENSE};md5=58d50a3d36f27f1a1e6089308a49b403"

DESCRIPTION = "Common mm-audio headers installation"

PBT_ARCH = "armv8-2a"

SRC_URI[sha256sum] = "077da78e3494883eb13cdf621bf2cb6dc4b5466bd4261a3be4ffb64904d60b60"

SRC_URI = "https://${PBT_ARTIFACTORY}/${PBT_BUILD_ID}/${PBT_BIN_PATH}/${BPN}_${PV}_${PBT_ARCH}.tar.gz"

ALLOW_EMPTY:${PN} = "1"
