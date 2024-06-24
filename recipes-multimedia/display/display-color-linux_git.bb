inherit qprebuilt pkgconfig

LICENSE          = "Qualcomm-Technologies-Inc.-Proprietary"
LIC_FILES_CHKSUM = "file://${QCOM_COMMON_LICENSE_DIR}${LICENSE};md5=58d50a3d36f27f1a1e6089308a49b403"

DESCRIPTION = "display Library"

SRC_URI[qcm6490.sha256sum] = "4a95eebdb86caa18a1a7f141023e9b6abe7c3ddd93cb1ce1d5081826bc4124ec"
SRC_URI[qcs9100.sha256sum] = "41340d215cd10f1d00252f2d577c92aa00dc8bf958fc79da41b521529fc4706e"

SRC_URI = "https://${PBT_ARTIFACTORY}/${PBT_BUILD_ID}/${PBT_BIN_PATH}/${BPN}_${PV}_${PBT_ARCH}.tar.gz;name=${PBT_ARCH}"

PACKAGE_ARCH = "${MACHINE_ARCH}"

INSANE_SKIP:${PN} = "dev-so"


ALLOW_EMPTY:${PN} = "1"
