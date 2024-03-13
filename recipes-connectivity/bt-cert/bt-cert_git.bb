inherit autotools pkgconfig

DESCRIPTION = "Bluetooth certification tool"
LICENSE = "Apache-2.0"

LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/${LICENSE};md5=89aea4e17d99a7cacdbeed46a0096b10"

SRC_URI += "git://git.codelinaro.org/clo/le/platform/vendor/qcom-opensource/bluetooth_ext.git;protocol=https;rev=5250a39cd07ffe7d8ff23909a1c90abb8e1b8c63;branch=bt-performant.qclinux.1.0.r1-rel;destsuffix=bluetooth/stack/bluetooth_ext \
           git://git.codelinaro.org/clo/le/platform/vendor/qcom-opensource/system/bt.git;protocol=https;rev=5c924afb77845b72591a41108f98ce91ae0c1373;branch=bt-performant.qclinux.1.0.r1-rel;destsuffix=bluetooth/stack/system/bt \
           git://git.codelinaro.org/clo/le/platform/vendor/qcom-opensource/bluetooth.git;protocol=https;rev=808a5314f33e9cb1507c0c0db102d82c15ee6f8f;branch=bt-performant.qclinux.1.0.r1-rel;destsuffix=bluetooth/bt_audio \
           "

S = "${WORKDIR}/bluetooth"

AUTOTOOLS_SCRIPT_PATH = "${S}/stack/bluetooth_ext/certification_tools"

DEPENDS  += "glib-2.0 btvendorhal libchrome fluoride"

EXTRA_OEMAKE += 'BT_SOURCE=${S}'

EXTRA_OECONF = " \
                --with-common-includes="${S}/bt_audio/hal/include/" \
                --with-glib \
                --with-lib-path=${STAGING_LIBDIR} \
                --with-chrome-includes="${STAGING_INCDIR}/chrome" \
               "
