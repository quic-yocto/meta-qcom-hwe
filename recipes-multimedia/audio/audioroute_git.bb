SUMMARY = "Audioroute for AROSP"
LICENSE = "Apache-2.0 & BSD-3-Clause-Clear"
LIC_FILES_CHKSUM += "file://audio_route.c;beginline=2;endline=20;md5=e35892022a2de7b18b106d01dafe2f0c"

SRC_URI += "git://git.codelinaro.org/clo/le/platform/vendor/qcom-opensource/audio-utils.git;protocol=https;rev=846767ec564fa83b6db26fb69d9cfa1f9da0dce2;branch=audio-utils.lnx.1.0.r1-rel;destsuffix=audio/opensource/audio-utils"
S = "${WORKDIR}/audio/opensource/audio-utils/audio-route"
DEPENDS = "glib-2.0 tinyalsa expat"
SOLIBS = ".so"
FILES_SOLIBSDEV = ""

RM_WORK_EXCLUDE += "${PN}"

inherit autotools pkgconfig
