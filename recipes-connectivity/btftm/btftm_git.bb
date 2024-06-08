inherit qprebuilt pkgconfig

LICENSE          = "Qualcomm-Technologies-Inc.-Proprietary"
LIC_FILES_CHKSUM = "file://${QCOM_COMMON_LICENSE_DIR}${LICENSE};md5=58d50a3d36f27f1a1e6089308a49b403"

DESCRIPTION = "Qualcomm Technologies ftmdaemon"

DEPENDS += "libnl diag glib-2.0 property-vault"

RDEPENDS:${PN} = "property-vault"


SRC_URI[sha256sum] = "26784574cc7c341d9c408d3b497e0318a46203638ba3beed51187df5da9efdd9"

SRC_URI = "https://${PBT_ARTIFACTORY}/${PBT_BUILD_ID}/${PBT_BIN_PATH}/${BPN}_${PV}_${PBT_ARCH}.tar.gz;name=${PBT_ARCH}"

PACKAGE_ARCH = "${MACHINE_ARCH}"
