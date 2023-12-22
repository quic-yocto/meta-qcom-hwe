inherit autotools-brokensep pkgconfig

HOMEPAGE         = "http://support.cdmatech.com"
DESCRIPTION = "CNSS"
LICENSE          = "Qualcomm-Technologies-Inc.-Proprietary"
LIC_FILES_CHKSUM = "file://${QCOM_COMMON_LICENSE_DIR}${LICENSE};md5=58d50a3d36f27f1a1e6089308a49b403"

PR = "r2"
PV = "7.0"

DEPENDS += "libcutils libnl liblog"

PACKAGE_ARCH = "${MACHINE_ARCH}"

CFLAGS += "-I ${STAGING_INCDIR}/libnl3"

SRC_URI = ""
