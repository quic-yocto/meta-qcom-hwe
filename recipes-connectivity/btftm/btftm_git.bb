inherit qprebuilt pkgconfig

LICENSE          = "Qualcomm-Technologies-Inc.-Proprietary"
LIC_FILES_CHKSUM = "file://${QCOM_COMMON_LICENSE_DIR}${LICENSE};md5=58d50a3d36f27f1a1e6089308a49b403"

DESCRIPTION = "Qualcomm Technologies ftmdaemon"

DEPENDS += "libnl diag glib-2.0 property-vault"

RDEPENDS:${PN} = "property-vault"


SRC_URI[qcm6490.sha256sum] = "26784574cc7c341d9c408d3b497e0318a46203638ba3beed51187df5da9efdd9"
SRC_URI[qcs9100.sha256sum] = "6c5a1476777ee70005c290224c8151005d6c1c9e62573f0ea5ef1f5fe9728d47"

SRC_URI = "https://${PBT_ARTIFACTORY}/${PBT_BUILD_ID}/${PBT_BIN_PATH}/${BPN}_${PV}_${PBT_ARCH}.tar.gz;name=${PBT_ARCH}"

PACKAGE_ARCH = "${MACHINE_ARCH}"
