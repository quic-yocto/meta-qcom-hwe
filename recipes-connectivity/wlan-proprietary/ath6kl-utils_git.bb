inherit autotools-brokensep pkgconfig

HOMEPAGE         = "http://support.cdmatech.com"
DESCRIPTION = "Qualcomm Atheros ath6kl utils."
LICENSE          = "Qualcomm-Technologies-Inc.-Proprietary"
LIC_FILES_CHKSUM = "file://${QCOM_COMMON_LICENSE_DIR}${LICENSE};md5=58d50a3d36f27f1a1e6089308a49b403"

DEPENDS += "diag libnl glib-2.0 libcutils"
PV = "1.0"
PR = "r2"

SRC_URI = ""

PACKAGE_ARCH = "${MACHINE_ARCH}"

EXTRA_OECONF = "--with-DMDM"
EXTRA_OECONF += "--with-glib"
EXTRA_OECONF += "--enable-target=${BASEMACHINE}"
