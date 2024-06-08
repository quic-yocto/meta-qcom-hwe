inherit qprebuilt pkgconfig systemd

LICENSE          = "Qualcomm-Technologies-Inc.-Proprietary"
LIC_FILES_CHKSUM = "file://${QCOM_COMMON_LICENSE_DIR}${LICENSE};md5=58d50a3d36f27f1a1e6089308a49b403"

DESCRIPTION = "Library and routing applications for diagnostic traffic"

DEPENDS += "syslog-plumber glib-2.0 qmi-framework diag"

SRC_URI[qcm6490.sha256sum] = "4103fe72a31c8f510053228b742638b23a2383d70bb3ea823cc3446a2961fabf"
SRC_URI[qcs9100.sha256sum] = "b51691861ec180f0095b7f573f4d082c69c279565c8f72c4fcfaf6a93519e8ad"

SRC_URI = "https://${PBT_ARTIFACTORY}/${PBT_BUILD_ID}/${PBT_BIN_PATH}/${BPN}_${PV}_${PBT_ARCH}.tar.gz;name=${PBT_ARCH}"

PACKAGE_ARCH = "${MACHINE_ARCH}"

FILES:${PN} += "${systemd_unitdir}/system/"

