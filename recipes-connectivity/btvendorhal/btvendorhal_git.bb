inherit autotools-brokensep

DESCRIPTION = "hardware btvendorhal headers"
HOMEPAGE = "http://codeaurora.org/"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/${LICENSE};md5=89aea4e17d99a7cacdbeed46a0096b10"

SRC_URI += "git://git.codelinaro.org/clo/le/platform/vendor/qcom-opensource/bluetooth.git;protocol=https;rev=8df29e610163fb38dddaf2ed905f2dbd98b72316;branch=bt-performant.qclinux.1.0.r1-rel;subdir=bt_audio \
           git://git.codelinaro.org/clo/le/platform/qcom-opensource/bt.git;protocol=https;rev=9d20f544ae0a0e3e5054fb6a7c89368361d428a1;branch=bt-performant.qclinux.1.0.r1-rel;subdir=btapp \
           git://git.codelinaro.org/clo/le/platform/vendor/qcom-opensource/system/bt.git;protocol=https;rev=a15c5eb0ba520877ba334a4a602b0a64a50ae657;branch=bt-performant.qclinux.1.0.r1-rel;subdir=stack/system/bt \
           "

S = "${WORKDIR}"

AUTOTOOLS_SCRIPT_PATH = "${S}/bt_audio"

EXTRA_OEMAKE += 'BT_SOURCE=${S}'

PR = "r1"
