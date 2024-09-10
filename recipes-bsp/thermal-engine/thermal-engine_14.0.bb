inherit qprebuilt pkgconfig systemd

LICENSE          = "Qualcomm-Technologies-Inc.-Proprietary"
LIC_FILES_CHKSUM = "file://${QCOM_COMMON_LICENSE_DIR}${LICENSE};md5=58d50a3d36f27f1a1e6089308a49b403"

DESCRIPTION = "Thermal Engine"

DEPENDS += "qmi-framework glib-2.0 libnl linux-kernel-qcom-headers"

SRC_URI[qcm6490.sha256sum] = "e0a404dcef2b778e93672142b42c42c57fbd38113eab77e9c35e234c1da7f20c"
SRC_URI[qcs9100.sha256sum] = "8cc7dff00c3cdaac971a7df4c1a364337c1e009001dfb9b400303ba2eced7ff4"
SRC_URI[qcs8300.sha256sum] = "4464b106ac0ffabeee3d398c70cafab082d26006312fc6bde33e74b6c0d7eb8c"

SRC_URI = "https://${PBT_ARTIFACTORY}/${PBT_BUILD_ID}/${PBT_BIN_PATH}/${BPN}_${PV}_${PBT_ARCH}.tar.gz;name=${PBT_ARCH}"
