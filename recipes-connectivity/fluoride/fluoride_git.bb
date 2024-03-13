inherit autotools-brokensep pkgconfig

DESCRIPTION = "Bluetooth Fluoride Stack"
LICENSE = "Apache-2.0"

LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/${LICENSE};md5=89aea4e17d99a7cacdbeed46a0096b10"

DEPENDS = "zlib libchrome glib-2.0 property-vault audioroute"
RDEPENDS:${PN} = "property-vault"

SRC_URI += "git://git.codelinaro.org/clo/le/platform/vendor/qcom-opensource/system/bt.git;protocol=https;rev=5c924afb77845b72591a41108f98ce91ae0c1373;branch=bt-performant.qclinux.1.0.r1-rel;destsuffix=bluetooth/stack/system/bt \
           git://git.codelinaro.org/clo/le/platform/vendor/qcom-opensource/bluetooth_ext.git;protocol=https;rev=5250a39cd07ffe7d8ff23909a1c90abb8e1b8c63;branch=bt-performant.qclinux.1.0.r1-rel;destsuffix=bluetooth/stack/bluetooth_ext \
           git://git.codelinaro.org/clo/le/platform/qcom-opensource/bt.git;protocol=https;rev=4292e7df90538d94d0e63afec52d78301e8411e2;branch=bt-performant.qclinux.1.0.r1-rel;destsuffix=bluetooth/btapp \
           git://git.codelinaro.org/clo/le/platform/vendor/qcom-opensource/bluetooth.git;protocol=https;rev=808a5314f33e9cb1507c0c0db102d82c15ee6f8f;branch=bt-performant.qclinux.1.0.r1-rel;destsuffix=bluetooth/bt_audio \
           "

S = "${WORKDIR}/bluetooth"
S_EXT = "${S}/stack/bluetooth_ext/system_bt_ext"

AUTOTOOLS_SCRIPT_PATH = "${S}/stack/system/bt"

EXTRA_OEMAKE += 'BT_SOURCE=${S}'

FILES_SOLIBSDEV = ""
FILES:${PN} += "${libdir}"
FILES:${PN} += "${sysconfdir}/bluetooth/*"
INSANE_SKIP:${PN} = "dev-so"

CPPFLAGS:append = " -DUSE_ANDROID_LOGGING -DUSE_LIBHW_AOSP"
CPPFLAGS:append = " -w -I${STAGING_INCDIR}"
CFLAGS:append = " -w -DNDEBUG  -I${STAGING_INCDIR}"

EXTRA_OECONF = " \
                --with-zlib \
                --with-common-includes="${S}/stack/system/bt" \
                --with-lib-path=${STAGING_LIBDIR} \
                --enable-static=yes \
                --with-chrome-includes="${STAGING_INCDIR}/chrome" \
               "

PACKAGE_ARCH = "${MACHINE_ARCH}"
do_install:append() {

	install -d ${D}${sysconfdir}/bluetooth/

	cd  ${D}/${libdir}/ && ln -s libbluetoothdefault.so.0 bluetooth.default.so
	cd  ${D}/${libdir}/ && ln -s libaudioa2dpdefault.so.0 audio.a2dp.default.so

	if [ -f ${S}/stack/system/bt/conf/auto_pair_devlist.conf ]; then
	   install -m 0660 ${S}/stack/system/bt/conf/auto_pair_devlist.conf ${D}${sysconfdir}/bluetooth/
	fi

	if [ -f ${S}/stack/system/bt/conf/bt_did.conf ]; then
	   install -m 0660 ${S}/stack/system/bt/conf/bt_did.conf ${D}${sysconfdir}/bluetooth/
	fi

	if [ -f ${S}/stack/system/bt/conf/bt_stack.conf ]; then
	   install -m 0660 ${S}/stack/system/bt/conf/bt_stack.conf ${D}${sysconfdir}/bluetooth/
	fi

	if [ -f ${S_EXT}/conf/interop_database.conf ]; then
		install -m 0660 ${S_EXT}/conf/interop_database.conf ${D}${sysconfdir}/bluetooth/
	fi

	if [ -f ${S_EXT}/conf/bt_profile.conf ]; then
		install -m 0660 ${S_EXT}/conf/bt_profile.conf ${D}${sysconfdir}/bluetooth/
	fi

	if [ -f ${S}/stack/system/bt/conf/iot_devlist.conf ]; then
	   install -m 0660 ${S}/stack/system/bt/conf/iot_devlist.conf ${D}${sysconfdir}/bluetooth/
	fi
}
