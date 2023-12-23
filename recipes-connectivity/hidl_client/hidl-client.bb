inherit autotools-brokensep pkgconfig

DESCRIPTION = "FTM HIDL client"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/files/common-licenses/\
${LICENSE};md5=89aea4e17d99a7cacdbeed46a0096b10"

DEPENDS = "common glib-2.0 liblog bttransport"

RDEPENDS:${PN} = "libcutils"

FILESPATH =+ "${WORKSPACE}:"
SRC_URI = "file://bluetooth/bt_audio/tools/"

S = "${WORKDIR}/bluetooth/bt_audio/tools"

EXTRA_OECONF = "--with-lib-path=${STAGING_LIBDIR} \
                --enable-static=yes \
                --enable-wlan=yes \
                --enable-bt=yes \
                --enable-debug=yes \
               "
