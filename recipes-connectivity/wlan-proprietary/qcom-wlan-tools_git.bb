inherit qprebuilt pkgconfig

LICENSE          = "Qualcomm-Technologies-Inc.-Proprietary"
LIC_FILES_CHKSUM = "file://${QCOM_COMMON_LICENSE_DIR}${LICENSE};md5=58d50a3d36f27f1a1e6089308a49b403"

DESCRIPTION = "Qualcomm Atheros common tools"

DEPENDS += "diag libnl glib-2.0 cld80211-lib libxml2 icu"

PV = "1.0"

SRC_URI[qcm6490.sha256sum] = "bae9d7082a2f54e213e707673c44efb326c0639dc347e97bec5ef78da7301e43"
SRC_URI[qcs9100.sha256sum] = "60f32b5b82da998c93bf2bb4fdad862bff9624f0c1b1a6c310f256d874732374"
SRC_URI[qcs8300.sha256sum] = "25100f64021f532fc496b7b058759176a43daab9cf213c6c57a9d2ec7b391e86"

SRC_URI = "https://${PBT_ARTIFACTORY}/${PBT_BUILD_ID}/${PBT_BIN_PATH}/${BPN}_${PV}_${PBT_ARCH}.tar.gz;name=${PBT_ARCH}"

FILES:${PN} += " \
	/usr/bin/* \
	/usr/sbin/* \
	/usr/lib/* \
	"

