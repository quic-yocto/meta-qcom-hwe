inherit qprebuilt pkgconfig

LICENSE          = "Qualcomm-Technologies-Inc.-Proprietary"
LIC_FILES_CHKSUM = "file://${QCOM_COMMON_LICENSE_DIR}${LICENSE};md5=58d50a3d36f27f1a1e6089308a49b403"

DESCRIPTION = "Time Genoff Library"

SRC_URI[qcm6490.sha256sum] = "f6d38cefe73cac474845bd31a8104323745fea89ba8d0e7785412f0c0678f209"
SRC_URI[qcs9100.sha256sum] = "f85d363bc959fe9f4d0d2d0dbabece0ce4184643dfc95bc7084337bd5ca8597c"

SRC_URI = "https://${PBT_ARTIFACTORY}/${PBT_BUILD_ID}/${PBT_BIN_PATH}/${BPN}_${PV}_${PBT_ARCH}.tar.gz;name=${PBT_ARCH}"

PACKAGE_ARCH = "${MACHINE_ARCH}"
