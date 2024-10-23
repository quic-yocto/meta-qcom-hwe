SUMMARY = "library to use and manage the QRTR bus"
DESCRIPTION = "libqrtr-glib is a glib-based library to use and manage the QRTR (Qualcomm IPC Router) bus"

LICENSE = "LGPL-2.1-or-later"
LIC_FILES_CHKSUM = "file://LICENSES/LGPL-2.1-or-later.txt;md5=4fbd65380cdd255951079008b364516c"

DEFAULT_PREFERENCE = "-1"

inherit meson pkgconfig gobject-introspection

DEPENDS = "glib-2.0"

SRCPROJECT = "git://git.codelinaro.org/clo/le/mobile-broadband/libqrtr-glib.git;protocol=https"
SRCBRANCH  = "telephony.qclinux.0.0.r1-rel"
SRCREV     = "7586514fbe1580e042039519bcc6dec537ded40a"

SRC_URI = "${SRCPROJECT};branch=${SRCBRANCH};destsuffix=mobile-broadband/libqrtr-glib"

S = "${WORKDIR}/mobile-broadband/libqrtr-glib"

EXTRA_OEMESON = " \
    -Dgtk_doc=false \
"
