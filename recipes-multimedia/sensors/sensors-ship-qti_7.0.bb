inherit qprebuilt pkgconfig systemd

LICENSE          = "Qualcomm-Technologies-Inc.-Proprietary"
LIC_FILES_CHKSUM = "file://${QCOM_COMMON_LICENSE_DIR}${LICENSE};md5=58d50a3d36f27f1a1e6089308a49b403"

DESCRIPTION = "Sensors-ship Library"

DEPENDS += "glib-2.0 property-vault syslog-plumber qmi-framework diag protobuf fastrpc"

SRC_URI[qcm6490.sha256sum] = "441165f64d06d2b10a76e1acd85be79a90c9771defcf8b540645fdf629239b8c"

SRC_URI = "https://${PBT_ARTIFACTORY}/${PBT_BUILD_ID}/${PBT_BIN_PATH}/${BPN}_${PV}_${PBT_ARCH}.tar.gz;name=${PBT_ARCH}"

FILES:${PN} += "${includedir}/*"
FILES:${PN} += "/usr/lib/*"
FILES:${PN} += "/usr/bin/*"
FILES:${PN}-dev  = "${libdir}/*.la ${includedir}"
FILES:${PN} += "${systemd_unitdir}/system/"
FILES:${PN} += "/etc/sensors/*"

INSANE_SKIP:${PN} = "dev-so"

SOLIBS = ".so"
FILES_SOLIBSDEV = ""

INHIBIT_PACKAGE_DEBUG_SPLIT = "1"

SYSTEMD_SERVICE:${PN} += "sscrpcd.service"
