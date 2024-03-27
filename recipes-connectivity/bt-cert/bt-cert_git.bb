inherit autotools pkgconfig

DESCRIPTION = "Bluetooth certification tool"
LICENSE = "Apache-2.0"

LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/${LICENSE};md5=89aea4e17d99a7cacdbeed46a0096b10"

SRC_URI = "git://git.codelinaro.org/clo/le/platform/vendor/qcom-opensource/bluetooth_ext.git;protocol=https;rev=68de38ed9163e5b1764042715fad380fe8d52e36;branch=bt-performant.qclinux.1.0.r1-rel;destsuffix=bluetooth/stack/bluetooth_ext \
           git://git.codelinaro.org/clo/le/platform/vendor/qcom-opensource/system/bt.git;protocol=https;rev=09b2d0f5ff746b9d0e7c79793af52ea5a0ecee44;branch=bt-performant.qclinux.1.0.r1-rel;destsuffix=bluetooth/stack/system/bt \
           git://git.codelinaro.org/clo/le/platform/vendor/qcom-opensource/bluetooth.git;protocol=https;rev=feac37f59b09e012753bb7f4f48121619d227f2b;branch=bt-performant.qclinux.1.0.r1-rel;destsuffix=bluetooth/bt_audio"

S = "${WORKDIR}/bluetooth"

AUTOTOOLS_SCRIPT_PATH = "${S}/stack/bluetooth_ext/certification_tools"

DEPENDS  += "glib-2.0 btvendorhal libchrome fluoride libbsd"

EXTRA_OEMAKE += 'BT_SOURCE=${S}'

EXTRA_OECONF = " \
                --with-common-includes="${S}/bt_audio/hal/include/" \
                --with-glib \
                --with-lib-path=${STAGING_LIBDIR} \
                --with-chrome-includes="${STAGING_INCDIR}/chrome" \
               "
