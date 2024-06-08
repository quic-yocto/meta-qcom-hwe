inherit qprebuilt pkgconfig

LICENSE          = "Qualcomm-Technologies-Inc.-Proprietary"
LIC_FILES_CHKSUM = "file://${QCOM_COMMON_LICENSE_DIR}${LICENSE};md5=58d50a3d36f27f1a1e6089308a49b403"

DESCRIPTION = "sva-eai"

DEPENDS += "sva-ml-commondwarf2-3 sva-common"

PBT_ARCH = "armv8-2a"

SRC_URI[sha256sum] = "78edeedb2a736b69f1c8aa116d2963b697a3250433ebfcd83a60981d772c1950"

SRC_URI = "https://${PBT_ARTIFACTORY}/${PBT_BUILD_ID}/${PBT_BIN_PATH}/${BPN}_${PV}_${PBT_ARCH}.tar.gz;name=${PBT_ARCH}"

SOLIBS = ".so"
FILES_SOLIBSDEV = ""
