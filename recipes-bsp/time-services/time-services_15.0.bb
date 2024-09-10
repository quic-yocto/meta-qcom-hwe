inherit qprebuilt pkgconfig systemd

LICENSE          = "Qualcomm-Technologies-Inc.-Proprietary"
LIC_FILES_CHKSUM = "file://${QCOM_COMMON_LICENSE_DIR}${LICENSE};md5=58d50a3d36f27f1a1e6089308a49b403"

DESCRIPTION = "Time Services Daemon"

DEPENDS += "virtual/kernel glib-2.0 diag qmi-framework"

RDEPENDS:${PN} += "qmi-framework"


SRC_URI[qcm6490.sha256sum] = "9b9bcb8b0cc47e34b000bf752275912b26c5e3acccfca5a773fb020ca459145e"
SRC_URI[qcs9100.sha256sum] = "67deb19a73545aa97551c0624654d798e1934db06df3d14aaf65815e5822b41c"
SRC_URI[qcs8300.sha256sum] = "47ba566df4c73a822ab645afca7dcfc500a3b39b23f55254fb0b5e0006566d3a"

SRC_URI = "https://${PBT_ARTIFACTORY}/${PBT_BUILD_ID}/${PBT_BIN_PATH}/${BPN}_${PV}_${PBT_ARCH}.tar.gz;name=${PBT_ARCH}"

FILES:${PN} += "${sysconfdir}/system/*"
FILES:${PN} += "${sysconfdir}/udev/rules.d/time-services.rules"

