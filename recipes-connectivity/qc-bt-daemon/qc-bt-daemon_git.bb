inherit autotools pkgconfig

HOMEPAGE         = "http://support.cdmatech.com"
LICENSE          = "Qualcomm-Technologies-Inc.-Proprietary"
LIC_FILES_CHKSUM = "file://${QCOM_COMMON_LICENSE_DIR}/${LICENSE};md5=92b1d0ceea78229551577d4284669bb8"

DESCRIPTION = "Bluetooth QC Daemon"
PR = "r0"
DEPENDS += "glib-2.0"
DEPENDS += "qmi-framework diag configdb dsutils common time-genoff xmllib"

EXTRA_OECONF = " \
               --with-glib \
               "

SRC_DIR = "${WORKSPACE}/bluetooth/proprietary/bluetooth_transport/qc-bt-daemon"

FILESPATH        =+ "${WORKSPACE}:"
SRC_URI          = "file://bluetooth/proprietary/bluetooth_transport/qc-bt-daemon/"

CPPFLAGS:append = " -DUSE_ANDROID_LOGGING "
LDFLAGS:append = " -llog "

S = "${WORKDIR}/bluetooth/proprietary/bluetooth_transport/qc-bt-daemon"
