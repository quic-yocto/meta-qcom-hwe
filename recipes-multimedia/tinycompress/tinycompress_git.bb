inherit autotools pkgconfig

DESCRIPTION = "Tinycompress Library"
LICENSE = "BSD-3-Clause & LGPL-2.1-only"
LIC_FILES_CHKSUM += "file://compress.c;beginline=5;endline=6;md5=f7d7b4acc5a50979ceb1b90726ef1489 \
                     file://include/tinycompress/tinycompress.h;beginline=4;endline=5;md5=f7d7b4acc5a50979ceb1b90726ef1489"

SRC_URI += "git://git.codelinaro.org/clo/le/platform/vendor/qcom/opensource/tinycompress.git;protocol=https;rev=35a75aac61cd4962b2d96c7595e0af9f0ef282cc;branch=audio-tinylibs.lnx.1.0.r63-rel;destsuffix=audio/opensource/tinycompress"

S = "${WORKDIR}/audio/opensource/tinycompress"
PV = "1.0"

DEPENDS = "virtual/kernel glib-2.0"

EXTRA_OECONF += "--with-glib"

SOLIBS = ".so"

FILES_SOLIBSDEV = ""
