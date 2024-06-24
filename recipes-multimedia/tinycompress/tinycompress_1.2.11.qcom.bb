inherit autotools pkgconfig

DESCRIPTION = "Tinycompress Library"
LICENSE = "BSD-3-Clause & LGPL-2.1-only"

LIC_FILES_CHKSUM += "file://COPYING;md5=cf9105c1a2d4405cbe04bbe3367373a0"

SRCREV = "e98e500873c9c4fdd752d1c85b6116da426a8a70"
SRC_URI += "git://github.com/alsa-project/tinycompress.git;protocol=https;branch=master \
            file://0001-tinycompress-update-Makerules.patch"

S = "${WORKDIR}/git"
PV = "1.2.11.qcom"

DEPENDS = "virtual/kernel glib-2.0"

FILES:${PN}-dev += "${libdir}$/*.so.* {libdir}/*.so"
FILES:${PN} += "${libdir}$/*.so.* {libdir}/*.so"
