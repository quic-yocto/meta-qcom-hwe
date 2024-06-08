inherit qprebuilt pkgconfig

LICENSE          = "Qualcomm-Technologies-Inc.-Proprietary"
LIC_FILES_CHKSUM = "file://${QCOM_COMMON_LICENSE_DIR}${LICENSE};md5=58d50a3d36f27f1a1e6089308a49b403"

DESCRIPTION = "display Library"

SRC_URI[qcm6490.sha256sum] = "21569d36a5599ad9717657592ea3537561746114c3df2f009c55614c08537eee"
SRC_URI[qcs9100.sha256sum] = "0c28891bccf0f8c1cba2f5b646eba1e19555d71a9ec622c65e3a170b22cbe02b"

SRC_URI = "https://${PBT_ARTIFACTORY}/${PBT_BUILD_ID}/${PBT_BIN_PATH}/${BPN}_${PV}_${PBT_ARCH}.tar.gz;name=${PBT_ARCH}"

PACKAGE_ARCH = "${MACHINE_ARCH}"

INSANE_SKIP:${PN} = "dev-so"


ALLOW_EMPTY:${PN} = "1"
