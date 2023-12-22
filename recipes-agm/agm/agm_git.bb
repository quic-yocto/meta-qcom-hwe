SUMMARY = "AGM for AROSP"
LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM += "file://service/src/agm.c;beginline=30;endline=31;md5=0f37b80cb1f8d808a27cca9f0cb5e0ac \
                     file://service/inc/public/agm/agm_api.h;beginline=31;endline=32;md5=2eeb25220b858bebc66a98390f40229e"

SRC_URI = "git://git.codelinaro.org/clo/le/platform/vendor/qcom/opensource/agm.git;protocol=https;rev=b4a74b03b8ffb274b740c5478cb006ffb6a5e4c6;branch=audio-platform-arintf.lnx.2.0.r3-rel \
           file://0001-service-device-Add-support-of-new-channel-map.patch"

S = "${WORKDIR}/git"

DEPENDS = "glib-2.0 tinyalsa args mm-audio-headers expat"

EXTRA_OECONF += "--with-glib --with-syslog --with-agm-no-ipc"

SOLIBS = ".so"
FILES_SOLIBSDEV = ""

INSANE_SKIP:${PN}-dev:append = " dev-elf"

inherit autotools pkgconfig
