inherit qprebuilt pkgconfig systemd

LICENSE          = "Qualcomm-Technologies-Inc.-Proprietary"
LIC_FILES_CHKSUM = "file://${QCOM_COMMON_LICENSE_DIR}${LICENSE};md5=58d50a3d36f27f1a1e6089308a49b403"

DESCRIPTION = "Sensors-services Library"

DEPENDS += "glib-2.0 property-vault syslog-plumber protobuf qcom-sensors-utils fastrpc"

QCM6490_SHA256SUM = "c6e5bd65949f8cfd8d5591fd830ae3c5320bbce3c29351352f94ff8fc8d7dd7c"

SRC_URI[qcm6490.sha256sum] = "${QCM6490_SHA256SUM}"

SRC_URI = "https://${PBT_ARTIFACTORY}/${PBT_BUILD_ID}/${PBT_BIN_PATH}/${BPN}_${PV}_${PBT_ARCH}.tar.gz;name=${PBT_ARCH}"

FILES:${PN} += "${includedir}/*"
FILES:${PN} += "/usr/lib/*"
FILES:${PN} += "/usr/bin/*"
FILES:${PN} += "${systemd_unitdir}/system/"
FILES:${PN}-dev  = "${libdir}/*.la ${includedir}"

INSANE_SKIP:${PN} = "dev-so"

SOLIBS = ".so"
FILES_SOLIBSDEV = ""

INHIBIT_PACKAGE_DEBUG_SPLIT = "1"

SYSTEMD_SERVICE:${PN} = "sscrpcd.service"
