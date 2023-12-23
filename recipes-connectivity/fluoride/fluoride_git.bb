inherit autotools-brokensep pkgconfig

DESCRIPTION = "Bluetooth Fluoride Stack"
HOMEPAGE = "http://codeaurora.org/"
LICENSE = "Apache-2.0"

LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/${LICENSE};md5=89aea4e17d99a7cacdbeed46a0096b10"

DEPENDS = "zlib libchrome glib-2.0 property-vault"
RDEPENDS:${PN} = "property-vault"

SRC_URI = "git://git.codelinaro.org/clo/le/platform/vendor/qcom-opensource/system/bt.git;protocol=https;rev=b12be0db7b8de53203efac31c0f3234281d05851;branch=bt-performant.qclinux.1.0.r1-rel;subdir=stack/system/bt \
           git://git.codelinaro.org/clo/le/platform/vendor/qcom-opensource/bluetooth_ext.git;protocol=https;rev=c894fb1cb8aee5a3150666159334938650958cbd;branch=bt-performant.qclinux.1.0.r1-rel;subdir=stack/bluetooth_ext \
           git://git.codelinaro.org/clo/le/platform/qcom-opensource/bt.git;protocol=https;rev=50f31639122c7496e0e13989c0ee97fd4ea5ac1f;branch=bt-performant.qclinux.1.0.r1-rel;subdir=btapp \
           git://git.codelinaro.org/clo/le/platform/vendor/qcom-opensource/bluetooth.git;protocol=https;rev=87f67a56b05ca12dac03b2d7c833d5e377139936;branch=bt-performant.qclinux.1.0.r1-rel;subdir=bt_audio \
           file://0001-stack-system-bt-makefile-fix.patch;patchdir=stack/system/bt \
           "

S = "${WORKDIR}"
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

	if [ -f ${S}/conf/auto_pair_devlist.conf ]; then
	   install -m 0660 ${S}/conf/auto_pair_devlist.conf ${D}${sysconfdir}/bluetooth/
	fi

	if [ -f ${S}/conf/bt_did.conf ]; then
	   install -m 0660 ${S}/conf/bt_did.conf ${D}${sysconfdir}/bluetooth/
	fi

	if [ -f ${S}/conf/bt_stack.conf ]; then
	   install -m 0660 ${S}/conf/bt_stack.conf ${D}${sysconfdir}/bluetooth/
	fi

	if [ -f ${S_EXT}/conf/interop_database.conf ]; then
		install -m 0660 ${S_EXT}/conf/interop_database.conf ${D}${sysconfdir}/bluetooth/
	fi

	if [ -f ${S_EXT}/conf/bt_profile.conf ]; then
		install -m 0660 ${S_EXT}/conf/bt_profile.conf ${D}${sysconfdir}/bluetooth/
	fi

	if [ -f ${S}/conf/iot_devlist.conf ]; then
	   install -m 0660 ${S}/conf/iot_devlist.conf ${D}${sysconfdir}/bluetooth/
	fi
}
