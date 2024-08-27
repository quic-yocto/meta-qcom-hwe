inherit qprebuilt pkgconfig systemd

LICENSE          = "Qualcomm-Technologies-Inc.-Proprietary"
LIC_FILES_CHKSUM = "file://${QCOM_COMMON_LICENSE_DIR}${LICENSE};md5=58d50a3d36f27f1a1e6089308a49b403"

DESCRIPTION = "Sensors-api Library"

DEPENDS += "glib-2.0 property-vault fastrpc syslog-plumber protobuf qcom-sensinghub"

QCM6490_SHA256SUM = "e7267cafef194d7a3433043cfdbb113417e2918ab1e5d43d7c686a0edb5a3127"

SRC_URI[qcm6490.sha256sum] = "${QCM6490_SHA256SUM}"

SRC_URI = "https://${PBT_ARTIFACTORY}/${PBT_BUILD_ID}/${PBT_BIN_PATH}/${BPN}_${PV}_${PBT_ARCH}.tar.gz;name=${PBT_ARCH}"

FILES:${PN} += "${includedir}/*"
FILES:${PN} += "/usr/lib/*"
FILES:${PN} += "/usr/bin/*"
FILES:${PN} += "/etc/sensors/*"
FILES:${PN}-dev  = "${libdir}/*.la ${includedir}"

INSANE_SKIP:${PN} = "dev-so"

INHIBIT_PACKAGE_DEBUG_SPLIT = "1"
