inherit qprebuilt pkgconfig systemd

LICENSE          = "Qualcomm-Technologies-Inc.-Proprietary"
LIC_FILES_CHKSUM = "file://${QCOM_COMMON_LICENSE_DIR}${LICENSE};md5=58d50a3d36f27f1a1e6089308a49b403"

DESCRIPTION = "Sensors-test-core Library"

DEPENDS += "glib-2.0 property-vault syslog-plumber protobuf diag qcom-sensors-api qcom-sensinghub qcom-sensors-utils qcom-sensors-core"

QCM6490_SHA256SUM = "0ac240b885ecf33fbbf6e83a6db82c54aa0c4d07b069e0027df9cdb0eb88931d"

SRC_URI[qcm6490.sha256sum] = "${QCM6490_SHA256SUM}"

SRC_URI = "https://${PBT_ARTIFACTORY}/${PBT_BUILD_ID}/${PBT_BIN_PATH}/${BPN}_${PV}_${PBT_ARCH}.tar.gz;name=${PBT_ARCH}"

FILES:${PN} += "${includedir}/*"
FILES:${PN} += "/usr/lib/*"
FILES:${PN} += "/usr/bin/*"

INSANE_SKIP:${PN} = "dev-so"

INHIBIT_PACKAGE_DEBUG_SPLIT = "1"
