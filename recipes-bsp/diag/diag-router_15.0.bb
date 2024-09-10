inherit qprebuilt pkgconfig systemd

LICENSE          = "Qualcomm-Technologies-Inc.-Proprietary"
LIC_FILES_CHKSUM = "file://${QCOM_COMMON_LICENSE_DIR}${LICENSE};md5=58d50a3d36f27f1a1e6089308a49b403"

DESCRIPTION = "Library and routing applications for diagnostic traffic"

DEPENDS += "syslog-plumber glib-2.0 qmi-framework diag"

SRC_URI[qcm6490.sha256sum] = "a94bcf8546ab01714691cda4bd7bf1869a71cce8e1847eb996326ad1e0c5de3b"
SRC_URI[qcs9100.sha256sum] = "f772c25a83e209feed99acf94ad6f9a4db623d22b2431d58baf17a28e784b28e"
SRC_URI[qcs8300.sha256sum] = "99d9e58b5f10c7848655822a8c7cfefed6536b1f8da82037c29980588a7215cc"

SRC_URI = "https://${PBT_ARTIFACTORY}/${PBT_BUILD_ID}/${PBT_BIN_PATH}/${BPN}_${PV}_${PBT_ARCH}.tar.gz;name=${PBT_ARCH}"

FILES:${PN} += "${systemd_unitdir}/system/"

