inherit autotools-brokensep pkgconfig

DESCRIPTION = "Bluetooth Vendor Library"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/${LICENSE};md5=89aea4e17d99a7cacdbeed46a0096b10"

DEPENDS = "common hci-qcomm-init glib-2.0"

QCOM_HW_BT_SRC ?= "git://git.codelinaro.org/clo/le/platform/hardware/qcom/bt.git;protocol=https"
QCOM_HW_BT_SRCBRANCH ?= "bt-performant.qclinux.1.0.r1-rel"
QCOM_HW_BT_SRCREV ?= "216da8dd028c739e82869447e64675f3c712ecf7"

QCOM_BLUETOOTH_SRC ?= "git://git.codelinaro.org/clo/le/platform/vendor/qcom-opensource/bluetooth.git;protocol=https"
QCOM_BLUETOOTH_SRCBRANCH ?= "bt-performant.qclinux.1.0.r1-rel"
QCOM_BLUETOOTH_SRCREV ?= "1710c237b493454dc93f41de09b50cd8d109f970"

SRC_URI = "${QCOM_HW_BT_SRC};branch=${QCOM_HW_BT_SRCBRANCH};rev=${QCOM_HW_BT_SRCREV};destsuffix=bluetooth/libbt-vendor \
           ${QCOM_BLUETOOTH_SRC};branch=${QCOM_BLUETOOTH_SRCBRANCH};rev=${QCOM_BLUETOOTH_SRCREV};destsuffix=bluetooth/bt_audio"

S = "${WORKDIR}/bluetooth"

BASEPRODUCT = "${@d.getVar('PRODUCT', False)}"

EXTRA_OECONF = "--with-common-includes="${S}/bt_audio/hal/include/" \
                --with-lib-path=${STAGING_LIBDIR} \
                --with-glib \
               "

FILES:${PN} += "${bindir}/bluetooth/*"

do_install:append () {
    install -d ${D}${bindir}/bluetooth
    install -m 755 ${S}/init.msm.bt.sh ${D}${bindir}/bluetooth/
}
