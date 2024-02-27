SUMMARY = "library to use and manage the QRTR bus"
DESCRIPTION = "libqrtr-glib is a glib-based library to use and manage the QRTR (Qualcomm IPC Router) bus"

LICENSE = "LGPL-2.1-or-later"
LIC_FILES_CHKSUM = "file://LICENSES/LGPL-2.1-or-later.txt;md5=4fbd65380cdd255951079008b364516c"

inherit meson pkgconfig gobject-introspection

DEPENDS = "glib-2.0"

SRC_URI += "git://git.codelinaro.org/clo/le/mobile-broadband/libqrtr-glib.git;protocol=https;rev=7586514fbe1580e042039519bcc6dec537ded40a;branch=telephony.qclinux.0.0.r1-rel"
S = "${WORKDIR}/git"

EXTRA_OEMESON = " \
    -Dgtk_doc=false \
"
