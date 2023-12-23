inherit autotools pkgconfig logging

DESCRIPTION = "Bluetooth application layer"
LICENSE = "Apache-2.0"
HOMEPAGE = "https://www.codeaurora.org/"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/${LICENSE};md5=89aea4e17d99a7cacdbeed46a0096b10"

SRC_URI += "git://git.codelinaro.org/clo/le/platform/qcom-opensource/bt.git;protocol=http;rev=50f31639122c7496e0e13989c0ee97fd4ea5ac1f;branch=bt-performant.qclinux.1.0.r1-rel;subdir=btapp \
           git://git.codelinaro.org/clo/le/platform/vendor/qcom-opensource/bluetooth.git;protocol=http;rev=87f67a56b05ca12dac03b2d7c833d5e377139936;branch=bt-performant.qclinux.1.0.r1-rel;subdir=bt_audio \
           git://git.codelinaro.org/clo/le/platform/vendor/qcom-opensource/system/bt.git;protocol=http;rev=b12be0db7b8de53203efac31c0f3234281d05851;branch=bt-performant.qclinux.1.0.r1-rel;subdir=stack/system/bt \
           git://git.codelinaro.org/clo/le/platform/vendor/qcom-opensource/bluetooth_ext.git;protocol=http;rev=c894fb1cb8aee5a3150666159334938650958cbd;branch=bt-performant.qclinux.1.0.r1-rel;subdir=stack/bluetooth_ext \
           file://0001-btapp-changes-in-Makefile.patch;patchdir=btapp \
           "

S = "${WORKDIR}"

EXTRA_OEMAKE += 'BT_SOURCE=${S}'

AUTOTOOLS_SCRIPT_PATH = "${S}/btapp/bt-app"

DEPENDS += "btvendorhal glib-2.0 property-vault libchrome fluoride"
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

        if [ -f ${S}/conf/bt_app.conf ]; then
           install -m 0660 ${S}/conf/bt_app.conf ${D}${sysconfdir}/bluetooth/
        fi

        if [ -f ${S}/conf/AdvertiserConfigFile.txt ]; then
           install -m 0660 ${S}/conf/AdvertiserConfigFile.txt ${D}${sysconfdir}/bluetooth/
        fi

        if [ -f ${S}/conf/ServerConfigFile.txt ]; then
           install -m 0660 ${S}/conf/ServerConfigFile.txt ${D}${sysconfdir}/bluetooth/
        fi

        if [ -f ${S}/conf/ext_to_mimetype.conf ]; then
           install -m 0660 ${S}/conf/ext_to_mimetype.conf ${D}${sysconfdir}/bluetooth/
        fi
}
