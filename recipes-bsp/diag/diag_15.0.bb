inherit qprebuilt pkgconfig systemd

LICENSE          = "Qualcomm-Technologies-Inc.-Proprietary"
LIC_FILES_CHKSUM = "file://${QCOM_COMMON_LICENSE_DIR}${LICENSE};md5=58d50a3d36f27f1a1e6089308a49b403"

DESCRIPTION = "Library and routing applications for diagnostic traffic"

DEPENDS += "glib-2.0 time-genoff"

SRC_URI[qcm6490.sha256sum] = "6e1eeb36ca38f902cf2cb3673d2c6f6377ae8430f8d69856d4e6887fe7a82e37"
SRC_URI[qcs9100.sha256sum] = "009bbebb23407c123734896a793ba75ebf82aa77f5e3304366c7aad9bcefaaab"

SRC_URI = "https://${PBT_ARTIFACTORY}/${PBT_BUILD_ID}/${PBT_BIN_PATH}/${BPN}_${PV}_${PBT_ARCH}.tar.gz;name=${PBT_ARCH}"

PACKAGE_ARCH = "${MACHINE_ARCH}"

FILES:${PN} += "${systemd_unitdir}/system/"

