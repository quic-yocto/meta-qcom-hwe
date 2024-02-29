inherit autotools pkgconfig

SUMMARY = "Audioroute for AROSP"
LICENSE = "Apache-2.0 & BSD-3-Clause-Clear"
LIC_FILES_CHKSUM = " \
    file://${COMMON_LICENSE_DIR}/Apache-2.0;md5=89aea4e17d99a7cacdbeed46a0096b10 \
    file://${COMMON_LICENSE_DIR}/BSD-3-Clause-Clear;md5=7a434440b651f4a472ca93716d01033a \
"

SRC_URI += "git://git.codelinaro.org/clo/le/platform/vendor/qcom-opensource/audio-utils.git;protocol=https;rev=846767ec564fa83b6db26fb69d9cfa1f9da0dce2;branch=audio-utils.lnx.1.0.r1-rel"
S = "${WORKDIR}/git/audio-route"
DEPENDS = "glib-2.0 tinyalsa expat"
SOLIBS = ".so"
FILES_SOLIBSDEV = ""
