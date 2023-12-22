inherit autotools pkgconfig

DESCRIPTION = "Bluetooth application layer"
LICENSE = "BSD-3-Clause"
HOMEPAGE = "https://www.codeaurora.org/"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/files/common-licenses/${LICENSE};md5=550794465ba0ec5312d6919e203a55f9"

FILESPATH =+ "${WORKSPACE}:"
SRC_URI = "file://bluetooth/btapp/bt-app/"

S = "${WORKDIR}/bluetooth/btapp/bt-app"

DEPENDS += "btvendorhal glib-2.0 btobex audiohal libchrome"
DEPENDS += "gstreamer1.0 gstreamer1.0-plugins-base orc qsthw-api gst-plugins"
DEPENDS:remove:mdm9607 = "audiohal"

CPPFLAGS:append = " -DUSE_ANDROID_LOGGING -DUSE_BT_OBEX -DUSE_LIBHW_AOSP -DUSE_GEN_GATT"
CFLAGS:append = " -DUSE_ANDROID_LOGGING "
LDFLAGS:append = " -llog "

EXTRA_OECONF = " \
            --with-common-includes="${WORKSPACE}/vendor/qcom/opensource/bluetooth/hal/include/" \
            --with-glib \
            --with-lib-path=${STAGING_LIBDIR} \
            --with-btobex \
            --with-gstreamer \
            --with-chrome-includes="${STAGING_INCDIR}/chrome" \
            --with-gengatt \
               "
EXTRA_OECONF += "--enable-target=${BASEMACHINE}"

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
