inherit qprebuilt pkgconfig

LICENSE          = "Qualcomm-Technologies-Inc.-Proprietary"
LIC_FILES_CHKSUM = "file://${QCOM_COMMON_LICENSE_DIR}${LICENSE};md5=58d50a3d36f27f1a1e6089308a49b403"

DESCRIPTION = "sva-kwd_enroll"

DEPENDS += "sva-ml-commondwarf2 sva-common sva-aml"

PBT_ARCH = "armv8-2a"

SRC_URI[sha256sum] = "39cb97dace9762bc84f8e440c00bb847464b822c1359ef22909b0c05793e6a79"

SRC_URI = "https://${PBT_ARTIFACTORY}/${PBT_BUILD_ID}/${PBT_BIN_PATH}/${BPN}_${PV}_${PBT_ARCH}.tar.gz"
