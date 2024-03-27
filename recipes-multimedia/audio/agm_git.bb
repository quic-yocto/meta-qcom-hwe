SUMMARY = "AGM for AROSP"
LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM += "file://service/src/agm.c;beginline=30;endline=31;md5=0f37b80cb1f8d808a27cca9f0cb5e0ac \
                     file://service/inc/public/agm/agm_api.h;beginline=31;endline=32;md5=2eeb25220b858bebc66a98390f40229e"

SRC_URI = "git://git.codelinaro.org/clo/le/platform/vendor/qcom/opensource/agm.git;protocol=https;rev=6756e171a9c05d5ef948a81c9904091dad40c8d6;branch=audio-platform-arintf.lnx.2.0.r3-rel;destsuffix=audio/opensource/agm \
           file://0001-service-device-Add-support-of-new-channel-map.patch"
S = "${WORKDIR}/audio/opensource/agm"
DEPENDS = "glib-2.0 tinyalsa tinycompress args mm-audio-headers expat"
EXTRA_OECONF += "--with-glib --with-syslog --with-agm-no-ipc"
SOLIBS = ".so"
FILES_SOLIBSDEV = ""

inherit autotools pkgconfig
