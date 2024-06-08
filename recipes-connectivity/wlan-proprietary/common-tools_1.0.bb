inherit qprebuilt pkgconfig

LICENSE          = "Qualcomm-Technologies-Inc.-Proprietary"
LIC_FILES_CHKSUM = "file://${QCOM_COMMON_LICENSE_DIR}${LICENSE};md5=58d50a3d36f27f1a1e6089308a49b403"

DESCRIPTION = "Qualcomm Atheros common tools"

DEPENDS += "diag libnl glib-2.0 cld80211-lib libxml2 icu"

SRC_URI[sha256sum] = "f38e6c7b562f57ef5195a3fc069fe5bea3afcd4b35927669fa5ee8a9798e2651"

PV = "1.0"
SRC_URI = "https://${PBT_ARTIFACTORY}/${PBT_BUILD_ID}/${PBT_BIN_PATH}/${BPN}_${PV}_${PBT_ARCH}.tar.gz;name=${PBT_ARCH}"

PACKAGE_ARCH = "${MACHINE_ARCH}"

FILES:${PN} += " \
	/usr/bin/* \
	/usr/sbin/* \
	/usr/lib/* \
	"

