inherit autotools pkgconfig

DESCRIPTION = "Bluetooth certification tool"
LICENSE = "Apache-2.0"
HOMEPAGE = "https://www.codeaurora.org/"

LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/${LICENSE};md5=89aea4e17d99a7cacdbeed46a0096b10"

SRC_URI += "git://git.codelinaro.org/clo/le/platform/vendor/qcom-opensource/bluetooth_ext.git;protocol=https;rev=17d14de31bbe88655f362a4527a631fef904be90;branch=bt-performant.qclinux.1.0.r1-rel;subdir=stack/bluetooth_ext \
           git://git.codelinaro.org/clo/le/platform/vendor/qcom-opensource/system/bt.git;protocol=https;rev=a15c5eb0ba520877ba334a4a602b0a64a50ae657;branch=bt-performant.qclinux.1.0.r1-rel;subdir=stack/system/bt \
           git://git.codelinaro.org/clo/le/platform/vendor/qcom-opensource/bluetooth.git;protocol=https;rev=8df29e610163fb38dddaf2ed905f2dbd98b72316;branch=bt-performant.qclinux.1.0.r1-rel;subdir=bt_audio \
           "

S = "${WORKDIR}"

AUTOTOOLS_SCRIPT_PATH = "${S}/stack/bluetooth_ext/certification_tools"

DEPENDS  += "glib-2.0 btvendorhal libchrome fluoride"

EXTRA_OEMAKE += 'BT_SOURCE=${S}'

EXTRA_OECONF = " \
                --with-common-includes="${S}/bt_audio/hal/include/" \
                --with-glib \
                --with-lib-path=${STAGING_LIBDIR} \
                --with-chrome-includes="${STAGING_INCDIR}/chrome" \
               "
