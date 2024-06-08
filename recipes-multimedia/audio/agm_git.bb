SUMMARY = "AGM for AROSP"

LICENSE = "BSD-3-Clause & LGPL-2.1-only"
LIC_FILES_CHKSUM += "file://service/src/agm.c;beginline=30;endline=31;md5=0f37b80cb1f8d808a27cca9f0cb5e0ac \
                     file://service/inc/public/agm/agm_api.h;beginline=31;endline=32;md5=2eeb25220b858bebc66a98390f40229e"

SRC_URI = "git://git.codelinaro.org/clo/le/platform/vendor/qcom/opensource/agm.git;protocol=https;rev=b8b348844fc2ccad19f91b3278c9af009e6657f5;branch=audio-core.lnx.1.0.r1-rel;destsuffix=audio/opensource/agm"

S = "${WORKDIR}/audio/opensource/agm"

DEPENDS = "glib-2.0 kvh2xml tinyalsa tinycompress args expat"
EXTRA_OECONF += "--with-glib --with-syslog --with-agm-no-ipc"
SOLIBS = ".so"
FILES_SOLIBSDEV = ""

inherit autotools pkgconfig
