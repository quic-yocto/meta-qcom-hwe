inherit autotools-brokensep

DESCRIPTION = "hardware btvendorhal headers"
HOMEPAGE = "http://codeaurora.org/"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/${LICENSE};md5=89aea4e17d99a7cacdbeed46a0096b10"

SRC_URI = "git://git.codelinaro.org/clo/le/platform/vendor/qcom-opensource/bluetooth.git;protocol=https;rev=87f67a56b05ca12dac03b2d7c833d5e377139936;branch=bt-performant.qclinux.1.0.r1-rel;subdir=bt_audio \
           git://git.codelinaro.org/clo/le/platform/qcom-opensource/bt.git;protocol=https;rev=50f31639122c7496e0e13989c0ee97fd4ea5ac1f;branch=bt-performant.qclinux.1.0.r1-rel;subdir=btapp \
           git://git.codelinaro.org/clo/le/platform/vendor/qcom-opensource/system/bt.git;protocol=https;rev=b12be0db7b8de53203efac31c0f3234281d05851;branch=bt-performant.qclinux.1.0.r1-rel;subdir=stack/system/bt \
           file://0001-bt_audio-changes-in-Makefile.patch;patchdir=bt_audio \
	   "

S = "${WORKDIR}"

AUTOTOOLS_SCRIPT_PATH = "${S}/bt_audio"

EXTRA_OEMAKE += 'BT_SOURCE=${S}'

PR = "r1"
