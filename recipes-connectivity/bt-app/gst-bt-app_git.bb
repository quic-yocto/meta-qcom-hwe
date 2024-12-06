inherit autotools pkgconfig

DESCRIPTION = "Bluetooth application layer"
LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/${LICENSE};md5=550794465ba0ec5312d6919e203a55f9"

SRCPROJECT = "git://git.codelinaro.org/clo/le/platform/qcom-opensource/bt.git;protocol=https"
SRCBRANCH  = "bt-performant.qclinux.1.0.r1-rel"
SRCREV     = "9844d95677d8fd2a145ada53dc7872de49d8dd0e"

SRC_URI = "${SRCPROJECT};branch=${SRCBRANCH};destsuffix=bluetooth/btapp"

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
