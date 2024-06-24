inherit qprebuilt

LICENSE          = "Qualcomm-Technologies-Inc.-Proprietary"
LIC_FILES_CHKSUM = "file://${QCOM_COMMON_LICENSE_DIR}${LICENSE};md5=58d50a3d36f27f1a1e6089308a49b403"

DESCRIPTION = "Camx"

PBT_ARCH = "armv8-2a"

SRC_URI[armv8-2a.sha256sum] = "f32b9ed9fcfde5821cba5cf48dacd7386a5fb4926d00963bd40a2c1c4cbfba48"

SRC_URI = "https://${PBT_ARTIFACTORY}/${PBT_BUILD_ID}/${PBT_BIN_PATH}/${BPN}_${PV}_${PBT_ARCH}.tar.gz;name=${PBT_ARCH}"

FILES:${PN}-dev = "/usr/include/*"


INSANE_SKIP = "1"
INSANE_SKIP:${PN} = "already-stripped"

