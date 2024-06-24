inherit qprebuilt pkgconfig systemd

LICENSE          = "Qualcomm-Technologies-Inc.-Proprietary"
LIC_FILES_CHKSUM = "file://${QCOM_COMMON_LICENSE_DIR}${LICENSE};md5=58d50a3d36f27f1a1e6089308a49b403"

DESCRIPTION = "Sensors-ship Library"

DEPENDS += "glib-2.0 property-vault syslog-plumber qmi-framework diag protobuf fastrpc libdmabufheap"

SRC_URI[qcm6490.sha256sum] = "c562c778ea8a64491a5452e2e124281ae467da87375e6e334477137d2ca06281"
SRC_URI[qcs9100.sha256sum] = "37213923ce978cf219e3d46da3fa115f5a6cd993b2d360d101fad7ca6a984a27"

SRC_URI = "https://${PBT_ARTIFACTORY}/${PBT_BUILD_ID}/${PBT_BIN_PATH}/${BPN}_${PV}_${PBT_ARCH}.tar.gz;name=${PBT_ARCH}"

PACKAGE_ARCH = "${MACHINE_ARCH}"

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
