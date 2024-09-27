inherit qprebuilt pkgconfig systemd

LICENSE          = "Qualcomm-Technologies-Inc.-Proprietary"
LIC_FILES_CHKSUM = "file://${QCOM_COMMON_LICENSE_DIR}${LICENSE};md5=58d50a3d36f27f1a1e6089308a49b403"

DESCRIPTION = "Library and routing applications for diagnostic traffic"

DEPENDS += "glib-2.0 time-genoff"

SRC_URI[qcm6490.sha256sum] = "49ed2ac0da43c3065264fb2772754a7f0a3fb6025c64bdc85990027396a54187"
SRC_URI[qcs9100.sha256sum] = "aa05a6855c267d2d262168cfd181260f486866a8bc1042b0f4f7a4339d651b4c"
SRC_URI[qcs8300.sha256sum] = "da6ef98f331ab7cfa2fec5c4df632ac6d2659f8d8f2af5c81a49da20ecaca743"

SRC_URI = "https://${PBT_ARTIFACTORY}/${PBT_BUILD_ID}/${PBT_BIN_PATH}/${BPN}_${PV}_${PBT_ARCH}.tar.gz;name=${PBT_ARCH}"

FILES:${PN} += "${systemd_unitdir}/system/"

