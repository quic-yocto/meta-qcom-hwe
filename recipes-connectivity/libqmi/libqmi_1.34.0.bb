SUMMARY = "libqmi is a library for talking to WWAN devices by QMI protocol"
DESCRIPTION = "libqmi is a glib-based library for talking to WWAN modems and \
               devices which speak the Qualcomm MSM Interface (QMI) protocol"
LICENSE = "GPL-2.0-or-later & LGPL-2.1-or-later"
LIC_FILES_CHKSUM = " \
    file://COPYING;md5=b234ee4d69f5fce4486a80fdaf4a4263 \
    file://COPYING.LIB;md5=4fbd65380cdd255951079008b364516c \
"

DEPENDS = "glib-2.0 glib-2.0-native"

inherit meson pkgconfig bash-completion gobject-introspection

SRC_URI += "git://git.codelinaro.org/clo/le/mobile-broadband/libqmi.git;protocol=https;rev=3f07d6e5b4677558543b3b4484ea88ad92257e92;branch=telephony.qclinux.0.0.r1-rel"

S = "${WORKDIR}/git"

PACKAGECONFIG ??= "udev qrtr"
PACKAGECONFIG[udev] = "-Dudev=true,-Dudev=false,libgudev"
PACKAGECONFIG[mbim] = "-Dmbim_qmux=true,-Dmbim_qmux=false,libmbim"
PACKAGECONFIG[qrtr] = "-Dqrtr=true,-Dqrtr=false,libqrtr-glib"

EXTRA_OEMESON = " \
    -Dgtk_doc=false \
    -Dman=false \
"
