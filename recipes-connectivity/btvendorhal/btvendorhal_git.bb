inherit autotools-brokensep

DESCRIPTION = "hardware btvendorhal headers"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/${LICENSE};md5=89aea4e17d99a7cacdbeed46a0096b10"

SRC_URI = "git://git.codelinaro.org/clo/le/platform/vendor/qcom-opensource/bluetooth.git;protocol=https;rev=feac37f59b09e012753bb7f4f48121619d227f2b;branch=bt-performant.qclinux.1.0.r1-rel;destsuffix=bluetooth/bt_audio \
           git://git.codelinaro.org/clo/le/platform/qcom-opensource/bt.git;protocol=https;rev=0b86b60d6735397ecc40b62500bf35432312fe90;branch=bt-performant.qclinux.1.0.r1-rel;destsuffix=bluetooth/btapp \
           git://git.codelinaro.org/clo/le/platform/vendor/qcom-opensource/system/bt.git;protocol=https;rev=91f7d13c41bff2f08133e07d10d8e389839ce5fe;branch=bt-performant.qclinux.1.0.r1-rel;destsuffix=bluetooth/stack/system/bt"

S = "${WORKDIR}/bluetooth"

AUTOTOOLS_SCRIPT_PATH = "${S}/bt_audio"

EXTRA_OEMAKE += 'BT_SOURCE=${S}'
