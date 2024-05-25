inherit qprebuilt pkgconfig

LICENSE          = "Qualcomm-Technologies-Inc.-Proprietary"
LIC_FILES_CHKSUM = "file://${QCOM_COMMON_LICENSE_DIR}${LICENSE};md5=58d50a3d36f27f1a1e6089308a49b403"

DESCRIPTION = "sva-gmm"

DEPENDS += "sva-common sva-listen-common sva-swmad"

PBT_ARCH = "armv8-2a"

SRC_URI[sha256sum] = "85691643c4940ae52a13adffb84e620db941e388799bf092172250bf04b65312"

SRC_URI = "https://${PBT_ARTIFACTORY}/${PBT_BUILD_ID}/${PBT_BIN_PATH}/${BPN}_${PV}_${PBT_ARCH}.tar.gz"
