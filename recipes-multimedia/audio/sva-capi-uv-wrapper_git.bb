inherit qprebuilt pkgconfig

LICENSE          = "Qualcomm-Technologies-Inc.-Proprietary"
LIC_FILES_CHKSUM = "file://${QCOM_COMMON_LICENSE_DIR}${LICENSE};md5=58d50a3d36f27f1a1e6089308a49b403"

DESCRIPTION = "sva-xs"

DEPENDS += "sva-common sva-uv capiv2-headers sva-listen-common sva-gmm sva-swmad sva-eai-utils sva-eai"

PBT_ARCH = "armv8-2a"

SRC_URI[sha256sum] = "4795cdb21c3f4e25fa895b0cd1e68f93eff8581130a27ae90f46b0fb9d4f27c0"

SRC_URI = "https://${PBT_ARTIFACTORY}/${PBT_BUILD_ID}/${PBT_BIN_PATH}/${BPN}_${PV}_${PBT_ARCH}.tar.gz"

SOLIBS = ".so"
FILES_SOLIBSDEV = ""
