inherit autotools-brokensep pkgconfig

HOMEPAGE         = "http://support.cdmatech.com"
DESCRIPTION = "Qualcomm Technologies ftmdaemon"
LICENSE          = "Qualcomm-Technologies-Inc.-Proprietary"
LIC_FILES_CHKSUM = "file://${QCOM_COMMON_LICENSE_DIR}${LICENSE};md5=58d50a3d36f27f1a1e6089308a49b403"

DEPENDS += "libnl diag glib-2.0 libcutils libbt-vendor hci-qcomm-init ath6kl-utils"

DEPENDS:append = " bttransport hidl-client"

PR = "r1"
PV = "1.0"

FILESPATH =+ "${WORKSPACE}:"

PACKAGE_ARCH = "${MACHINE_ARCH}"

SRC_URI = ""

USE_LIBBT_VENDOR="no"
HCI_INC="${WORKSPACE}/vendor/qcom/opensource/system/bt/hci/include"
HCI_QCOM_INIT="${WORKSPACE}/vendor/qcom/proprietary/bt/hci_qcomm_init"
HIDL_CLIENT_INC="${WORKSPACE}/vendor/qcom/opensource/bluetooth/tools/hidl_client/inc"

BASEPRODUCT = "${@d.getVar('PRODUCT', False)}"

EXTRA_OECONF = " \
                --with-glib \
                --enable-wlan=yes \
                --enable-bt=yes \
                --enable-debug=yes \
                --enable-target=${BASEMACHINE} \
                --enable-rome=${BASEPRODUCT} \
                "

EXTRA_OECONF += "HCI_INC=${HCI_INC} HCI_QCOM_INIT=${HCI_QCOM_INIT} HIDL_CLIENT_INC=${HIDL_CLIENT_INC}"
