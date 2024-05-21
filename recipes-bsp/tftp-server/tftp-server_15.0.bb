inherit qprebuilt pkgconfig systemd

LICENSE          = "Qualcomm-Technologies-Inc.-Proprietary"
LIC_FILES_CHKSUM = "file://${QCOM_COMMON_LICENSE_DIR}${LICENSE};md5=58d50a3d36f27f1a1e6089308a49b403"

DESCRIPTION = "tftp_server server module"

DEPENDS += "glib-2.0 virtual/kernel qmi-framework property-vault libcap"

PBT_ARCH = "armv8-2a"

SRC_URI[sha256sum] = "d5c0cbf756e58dc484d2092b9d99c5b3c244ac7bf108e8f73319a1c8753ec11c"

SRC_URI = "https://${PBT_ARTIFACTORY}/${PBT_BUILD_ID}/${PBT_BIN_PATH}/${BPN}_${PV}_${PBT_ARCH}.tar.gz"

FILES:${PN} += "/system /data"
FILES:${PN} += "/lib/systemd/*"


SYSTEMD_SERVICE:${PN} = "tftp_server.service"
