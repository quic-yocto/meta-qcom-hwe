inherit qprebuilt pkgconfig

LICENSE          = "Qualcomm-Technologies-Inc.-Proprietary"
LIC_FILES_CHKSUM = "file://${QCOM_COMMON_LICENSE_DIR}${LICENSE};md5=58d50a3d36f27f1a1e6089308a49b403"

DESCRIPTION = "sva-common"

PBT_ARCH = "armv8-2a"

SRC_URI[sha256sum] = "6f4d3d0e501bc1f6e789b3c16556f35e18e6509551c75370e7e8dd39fd2bb629"

SRC_URI = "https://${PBT_ARTIFACTORY}/${PBT_BUILD_ID}/${PBT_BIN_PATH}/${BPN}_${PV}_${PBT_ARCH}.tar.gz;name=${PBT_ARCH}"
