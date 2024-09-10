DESCRIPTION = "A toolkit to interact with the virtualization capabilities of recent versions of Linux."
HOMEPAGE = "http://libvirt.org"
LICENSE = "LGPL-2.1-only"
LIC_FILES_CHKSUM = "file://COPYING;md5=4fbd65380cdd255951079008b364516c"

DEPENDS = "glib-2.0 libvirt libxml2"

SRC_URI = "git://gitlab.com/libvirt/libvirt-glib;protocol=https;branch=master"

SRCREV = "e0bfc34682744a74b850fa217e9c206a9eb80612"
S = "${WORKDIR}/git"

inherit meson pkgconfig gobject-introspection vala
GIR_MESON_OPTION = ''
