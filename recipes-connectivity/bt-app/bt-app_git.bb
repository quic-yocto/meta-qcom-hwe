inherit autotools pkgconfig logging

DESCRIPTION = "Bluetooth application layer"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/${LICENSE};md5=89aea4e17d99a7cacdbeed46a0096b10"

QCOM_BT_SRC ?= "git://git.codelinaro.org/clo/le/platform/qcom-opensource/bt.git;protocol=https"
QCOM_BT_SRCBRANCH ?= "bt-performant.qclinux.1.0.r1-rel"
QCOM_BT_SRCREV    ?= "9844d95677d8fd2a145ada53dc7872de49d8dd0e"

QCOM_BLUETOOTH_SRC ?= "git://git.codelinaro.org/clo/le/platform/vendor/qcom-opensource/bluetooth.git;protocol=https"
QCOM_BLUETOOTH_SRCBRANCH ?= "bt-performant.qclinux.1.0.r1-rel"
QCOM_BLUETOOTH_SRCREV    ?= "1710c237b493454dc93f41de09b50cd8d109f970"

QCOM_SYSTEM_BT_SRC ?= "git://git.codelinaro.org/clo/le/platform/vendor/qcom-opensource/system/bt.git;protocol=https"
QCOM_SYSTEM_BT_SRCBRANCH ?= "bt-performant.qclinux.1.0.r1-rel"
QCOM_SYSTEM_BT_SRCREV    ?= "677327d9157df86311d8be4a3fd60fb2d5169ad2"

QCOM_BLUETOOTH_EXT_SRC ?= "git://git.codelinaro.org/clo/le/platform/vendor/qcom-opensource/bluetooth_ext.git;protocol=https"
QCOM_BLUETOOTH_EXT_SRCBRANCH ?= "bt-performant.qclinux.1.0.r1-rel"
QCOM_BLUETOOTH_EXT_SRCREV    ?= "996bbb712e62c5c22489cd84fad1a93a91d65ddc"

SRC_URI = "${QCOM_BT_SRC};branch=${QCOM_BT_SRCBRANCH};rev=${QCOM_BT_SRCREV};destsuffix=bluetooth/btapp \
           ${QCOM_BLUETOOTH_SRC};branch=${QCOM_BLUETOOTH_SRCBRANCH};rev=${QCOM_BLUETOOTH_SRCREV};destsuffix=bluetooth/bt_audio \
           ${QCOM_SYSTEM_BT_SRC};branch=${QCOM_SYSTEM_BT_SRCBRANCH};rev=${QCOM_SYSTEM_BT_SRCREV};destsuffix=bluetooth/stack/system/bt \
           ${QCOM_BLUETOOTH_EXT_SRC};branch=${QCOM_BLUETOOTH_EXT_SRCBRANCH};rev=${QCOM_BLUETOOTH_EXT_SRCREV};destsuffix=bluetooth/stack/bluetooth_ext"

S = "${WORKDIR}/bluetooth"

EXTRA_OEMAKE += 'BT_SOURCE=${S}'

AUTOTOOLS_SCRIPT_PATH = "${S}/btapp/bt-app"

DEPENDS += "btvendorhal glib-2.0 property-vault libchrome fluoride qcom-audioroute qcom-pa-bt-audio libbsd"
RDEPENDS:${PN} = "property-vault"

CPPFLAGS:append = " -DUSE_LIBHW_AOSP -DUSE_GEN_GATT"
CPPFLAGS:qcm6490 = " -DSUPPORT_ESL_AP"
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
