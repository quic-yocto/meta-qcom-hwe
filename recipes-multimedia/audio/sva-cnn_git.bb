inherit qprebuilt pkgconfig

LICENSE          = "Qualcomm-Technologies-Inc.-Proprietary"
LIC_FILES_CHKSUM = "file://${QCOM_COMMON_LICENSE_DIR}${LICENSE};md5=58d50a3d36f27f1a1e6089308a49b403"

DESCRIPTION = "sva-cnn"

DEPENDS += "sva-common sva-listen-common sva-swmad sva-ml-commondwarf sva-ml-commondwarf2 sva-ml-commondwarf2-3 sva-eai capiv2-headers sva-eai-utils pdk-wrapper"

PBT_ARCH = "armv8-2a"

SRC_URI[armv8-2a.sha256sum] = "f026d741bab12e2a189833a566ea3492f80539bc25559766799c7397dbd87b5d"

SRC_URI = "https://${PBT_ARTIFACTORY}/${PBT_BUILD_ID}/${PBT_BIN_PATH}/${BPN}_${PV}_${PBT_ARCH}.tar.gz;name=${PBT_ARCH}"

SOLIBS = ".so"
FILES_SOLIBSDEV = ""
