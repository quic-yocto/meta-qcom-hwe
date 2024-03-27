inherit autotools pkgconfig

LICENSE          = "Qualcomm-Technologies-Inc.-Proprietary"
LIC_FILES_CHKSUM = "file://${QCOM_COMMON_LICENSE_DIR}/${LICENSE};md5=92b1d0ceea78229551577d4284669bb8"

DESCRIPTION = "Bluetooth QC Daemon"

DEPENDS += "glib-2.0"
DEPENDS += "qmi-framework diag configdb dsutils common time-genoff xmllib"

S = "${WORKDIR}/bluetooth/proprietary/bluetooth_transport/qc-bt-daemon"

EXTRA_OECONF = " \
               --with-glib \
               "

SRC_URI          = "git://qpm-git.qualcomm.com/home2/git/revision-history/platform/vendor/qcom-proprietary/bluetooth.git;protocol=https;rev=c199b2edf1952a825a4d367b4877e0736b76befc;branch=1020-bt-performant.qclinux.1.0.r1-rel;destsuffix=bluetooth/proprietary/bluetooth_transport"
