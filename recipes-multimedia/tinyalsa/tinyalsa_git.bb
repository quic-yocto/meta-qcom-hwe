inherit autotools pkgconfig

DESCRIPTION = "Tinyalsa Library"
LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM += "file://pcm_plugin.c;beginline=3;endline=4;md5=5b09b9f05328bd4b96f81d687f178fb3 \
                     file://include/tinyalsa/pcm_plugin.h;beginline=2;endline=3;md5=f72acfa19e2065b81d8ab6146ce138d7"

SRC_URI += "git://git.codelinaro.org/clo/le/platform/vendor/qcom/opensource/tinyalsa.git;protocol=https;rev=7ffd36603eccf1dfddf33208b7cc44a234289b84;branch=audio-tinylibs.lnx.1.0.r63-rel;destsuffix=audio/opensource/tinyalsa"

S = "${WORKDIR}/audio/opensource/tinyalsa"

PV = "1.0"

DEPENDS = "glib-2.0"

EXTRA_OECONF += "--with-glib"

SOLIBS = ".so"

FILES_SOLIBSDEV = ""
