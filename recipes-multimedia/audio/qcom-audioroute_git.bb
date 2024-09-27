SUMMARY = "Audioroute for AROSP"
LICENSE = "Apache-2.0 & BSD-3-Clause-Clear"
LIC_FILES_CHKSUM += "file://audio_route.c;beginline=2;endline=20;md5=84ef494e74239b3871a4da47f3702691"

SRC_URI = "git://git.codelinaro.org/clo/le/platform/vendor/qcom-opensource/audio-utils.git;protocol=https;rev=1154a270f52ff7c2e21cf9444b8799f98fe06b8f;branch=audio-utils.lnx.1.0.r1-rel;destsuffix=audio/opensource/audio-utils"
S = "${WORKDIR}/audio/opensource/audio-utils/audio-route"
DEPENDS = "glib-2.0 tinyalsa expat"
SOLIBS = ".so"
FILES_SOLIBSDEV = ""

RM_WORK_EXCLUDE += "${PN}"

inherit autotools pkgconfig
