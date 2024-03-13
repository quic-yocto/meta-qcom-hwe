inherit autotools pkgconfig logging

DESCRIPTION = "Bluetooth application layer"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/${LICENSE};md5=89aea4e17d99a7cacdbeed46a0096b10"

SRC_URI += "git://git.codelinaro.org/clo/le/platform/qcom-opensource/bt.git;protocol=https;rev=4292e7df90538d94d0e63afec52d78301e8411e2;branch=bt-performant.qclinux.1.0.r1-rel;destsuffix=bluetooth/btapp \
           git://git.codelinaro.org/clo/le/platform/vendor/qcom-opensource/bluetooth.git;protocol=https;rev=808a5314f33e9cb1507c0c0db102d82c15ee6f8f;branch=bt-performant.qclinux.1.0.r1-rel;destsuffix=bluetooth/bt_audio \
           git://git.codelinaro.org/clo/le/platform/vendor/qcom-opensource/system/bt.git;protocol=https;rev=5c924afb77845b72591a41108f98ce91ae0c1373;branch=bt-performant.qclinux.1.0.r1-rel;destsuffix=bluetooth/stack/system/bt \
           git://git.codelinaro.org/clo/le/platform/vendor/qcom-opensource/bluetooth_ext.git;protocol=https;rev=5250a39cd07ffe7d8ff23909a1c90abb8e1b8c63;branch=bt-performant.qclinux.1.0.r1-rel;destsuffix=bluetooth/stack/bluetooth_ext \
           "

S = "${WORKDIR}/bluetooth"

EXTRA_OEMAKE += 'BT_SOURCE=${S}'

AUTOTOOLS_SCRIPT_PATH = "${S}/btapp/bt-app"

DEPENDS += "btvendorhal glib-2.0 property-vault libchrome fluoride audioroute pa-bt-audio"
RDEPENDS:${PN} = "property-vault"

CPPFLAGS:append = " -DUSE_LIBHW_AOSP -DUSE_GEN_GATT"
SECURITY_CFLAGS = "${SECURITY_NO_PIE_CFLAGS}"

EXTRA_OECONF = " \
                --with-common-includes="${S}/bt_audio/hal/include/" \
                --with-glib \
                --with-lib-path=${STAGING_LIBDIR} \
                --with-chrome-includes="${STAGING_INCDIR}/chrome" \
                --with-gengatt \
               "
PACKAGE_ARCH = "${MACHINE_ARCH}"
FILES:${PN} += "${sysconfdir}/bluetooth/*"
FILES:${PN} += "${userfsdatadir}/misc/bluetooth/*"

do_install:append() {
        install -d ${D}${sysconfdir}/bluetooth/

        #create /data/misc/bluetooth/ folder
        #install -d ${D}${userfsdatadir}/misc/bluetooth/

        if [ -f ${S}/btapp/bt-app/conf/bt_app.conf ]; then
           install -m 0660 ${S}/btapp/bt-app/conf/bt_app.conf ${D}${sysconfdir}/bluetooth/
        fi

        if [ -f ${S}/btapp/bt-app/conf/AdvertiserConfigFile.txt ]; then
           install -m 0660 ${S}/btapp/bt-app/conf/AdvertiserConfigFile.txt ${D}${sysconfdir}/bluetooth/
        fi

        if [ -f ${S}/btapp/bt-app/conf/ServerConfigFile.txt ]; then
           install -m 0660 ${S}/btapp/bt-app/conf/ServerConfigFile.txt ${D}${sysconfdir}/bluetooth/
        fi

        if [ -f ${S}/btapp/bt-app/conf/ext_to_mimetype.conf ]; then
           install -m 0660 ${S}/btapp/bt-app/conf/ext_to_mimetype.conf ${D}${sysconfdir}/bluetooth/
        fi
}
