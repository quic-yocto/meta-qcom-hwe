inherit autotools-brokensep pkgconfig

DESCRIPTION = "Qualcomm Atheros WLAN CLD utils"
LICENSE = "Qualcomm-Technologies-Inc.-Proprietary"
LIC_FILES_CHKSUM = "file://${QCOM_COMMON_LICENSE_DIR}${LICENSE};md5=58d50a3d36f27f1a1e6089308a49b403"

QCACLD = " qcacld-wlan"

SRC_URI = ""

DEPENDS += "diag dsutils libcutils libnl qmi qmi-framework wpa-supplicant-qcacld ${QCACLD}"

FILES:${PN} += "${sysconfdir}/misc/wifi/*"

EXTRA_OECONF = "--with-glib \
                --enable-target=${BASEMACHINE}"


PACKAGE_ARCH = "${MACHINE_ARCH}"
