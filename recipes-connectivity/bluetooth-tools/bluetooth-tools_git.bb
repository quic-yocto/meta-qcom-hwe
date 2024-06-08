inherit qprebuilt pkgconfig

LICENSE          = "Qualcomm-Technologies-Inc.-Proprietary"
LIC_FILES_CHKSUM = "file://${QCOM_COMMON_LICENSE_DIR}${LICENSE};md5=58d50a3d36f27f1a1e6089308a49b403"

DESCRIPTION = "Bluetooth tools layer"

DEPENDS += "dbus glib-2.0"

PBT_ARCH = "armv8-2a"

SRC_URI[armv8-2a.sha256sum] = "8f0aef751e91bc0d046e3889b99b41faa2f1b3c9947582a32e9e616b0dd5d210"

SRC_URI = "https://${PBT_ARTIFACTORY}/${PBT_BUILD_ID}/${PBT_BIN_PATH}/${BPN}_${PV}_${PBT_ARCH}.tar.gz;name=${PBT_ARCH}"

INSANE_SKIP:${PN} += "file-rdeps"

