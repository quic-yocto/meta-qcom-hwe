SUMMARY = "AGM for AROSP"

LICENSE = "BSD-3-Clause & LGPL-2.1-only"
LIC_FILES_CHKSUM += "file://service/src/agm.c;beginline=30;endline=31;md5=0f37b80cb1f8d808a27cca9f0cb5e0ac \
                     file://service/inc/public/agm/agm_api.h;beginline=31;endline=32;md5=2eeb25220b858bebc66a98390f40229e"

inherit autotools pkgconfig

SRCPROJECT = "git://git.codelinaro.org/clo/le/platform/vendor/qcom/opensource/agm.git;protocol=https"
SRCBRANCH  = "audio-core.lnx.1.0.r1-rel"
SRCREV     = "2a57e93284279c513307b2d547ad088788b4005c"

SRC_URI = "${SRCPROJECT};branch=${SRCBRANCH};destsuffix=audio/opensource/agm"

S = "${WORKDIR}/audio/opensource/agm"

DEPENDS = "glib-2.0 qcom-kvh2xml tinyalsa tinycompress qcom-args expat"
EXTRA_OECONF += "--with-glib --with-syslog --with-agm-no-ipc"
SOLIBS = ".so"
FILES_SOLIBSDEV = ""
