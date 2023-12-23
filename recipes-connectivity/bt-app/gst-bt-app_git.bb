inherit autotools pkgconfig

DESCRIPTION = "Bluetooth application layer"
LICENSE = "BSD-3-Clause"
HOMEPAGE = "https://www.codeaurora.org/"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/files/common-licenses/${LICENSE};md5=550794465ba0ec5312d6919e203a55f9"

SRC_URI += "git://git.codelinaro.org/clo/le/platform/qcom-opensource/bt.git;protocol=http;rev=50f31639122c7496e0e13989c0ee97fd4ea5ac1f;branch=bt-performant.qclinux.1.0.r1-rel;subdir=btapp \
           git://git.codelinaro.org/clo/le/platform/vendor/qcom-opensource/bluetooth.git;protocol=http;rev=87f67a56b05ca12dac03b2d7c833d5e377139936;branch=bt-performant.qclinux.1.0.r1-rel;subdir=bt_audio \
           "

S = "${WORKDIR}"

EXTRA_OEMAKE += 'BT_SOURCE=${S}'

AUTOTOOLS_SCRIPT_PATH = "${S}/btapp/bt-app"

DEPENDS += "btvendorhal glib-2.0 btobex audiohal libchrome"
DEPENDS += "gstreamer1.0 gstreamer1.0-plugins-base orc qsthw-api gst-plugins"

CPPFLAGS:append = " -DUSE_ANDROID_LOGGING -DUSE_BT_OBEX -DUSE_LIBHW_AOSP -DUSE_GEN_GATT"
CFLAGS:append = " -DUSE_ANDROID_LOGGING "
LDFLAGS:append = " -llog "

EXTRA_OECONF = " \
            --with-common-includes="${S}/bt_audio/hal/include/" \
            --with-glib \
            --with-lib-path=${STAGING_LIBDIR} \
            --with-btobex \
            --with-gstreamer \
            --with-chrome-includes="${STAGING_INCDIR}/chrome" \
            --with-gengatt \
               "
FILES:${PN} += "${sysconfdir}/bluetooth/*"

do_install:append() {
        install -d ${D}${sysconfdir}/bluetooth/

        if [ -f ${S}/conf/bt_app.conf ]; then
           install -m 0660 ${S}/conf/bt_app.conf ${D}${sysconfdir}/bluetooth/
        fi

        if [ -f ${S}/conf/ext_to_mimetype.conf ]; then
           install -m 0660 ${S}/conf/ext_to_mimetype.conf ${D}${sysconfdir}/bluetooth/
        fi
}
