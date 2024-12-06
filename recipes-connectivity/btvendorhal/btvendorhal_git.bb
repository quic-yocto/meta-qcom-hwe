inherit autotools-brokensep

DESCRIPTION = "hardware btvendorhal headers"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/${LICENSE};md5=89aea4e17d99a7cacdbeed46a0096b10"

QCOM_BLUETOOTH_SRC ?= "git://git.codelinaro.org/clo/le/platform/vendor/qcom-opensource/bluetooth.git;protocol=https"
QCOM_BLUETOOTH_SRCBRANCH ?= "bt-performant.qclinux.1.0.r1-rel"
QCOM_BLUETOOTH_SRCREV ?= "1710c237b493454dc93f41de09b50cd8d109f970"

QCOM_BT_SRC ?= "git://git.codelinaro.org/clo/le/platform/qcom-opensource/bt.git;protocol=https"
QCOM_BT_SRCBRANCH ?= "bt-performant.qclinux.1.0.r1-rel"
QCOM_BT_SRCREV ?= "9844d95677d8fd2a145ada53dc7872de49d8dd0e"

QCOM_SYSTEM_BT_SRC ?= "git://git.codelinaro.org/clo/le/platform/vendor/qcom-opensource/system/bt.git;protocol=https"
QCOM_SYSTEM_BT_SRCBRANCH ?= "bt-performant.qclinux.1.0.r1-rel"
QCOM_SYSTEM_BT_SRCBRANCH ?= "677327d9157df86311d8be4a3fd60fb2d5169ad2"

SRC_URI = "${QCOM_BLUETOOTH_SRC};branch=${QCOM_BLUETOOTH_SRCBRANCH};rev=${QCOM_BLUETOOTH_SRCREV};destsuffix=bluetooth/bt_audio \
           ${QCOM_BT_SRC};branch=${QCOM_BT_SRCBRANCH};rev=${QCOM_BT_SRCREV};destsuffix=bluetooth/btapp \
           ${QCOM_SYSTEM_BT_SRC};branch=${QCOM_SYSTEM_BT_SRCBRANCH};rev=${QCOM_SYSTEM_BT_SRCBRANCH};destsuffix=bluetooth/stack/system/bt"

S = "${WORKDIR}/bluetooth"

AUTOTOOLS_SCRIPT_PATH = "${S}/bt_audio"

EXTRA_OEMAKE += 'BT_SOURCE=${S}'
