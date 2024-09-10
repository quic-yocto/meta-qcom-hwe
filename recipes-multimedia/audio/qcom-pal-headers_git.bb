inherit autotools pkgconfig

DESCRIPTION = "Common mm-audio headers installation"

LICENSE = "BSD-3-Clause & BSD-3-Clause-Clear"
LIC_FILES_CHKSUM += "file://PalDefs.h;beginline=30;endline=31;md5=e733afaf233fbcbc22769d0a9bda0b3e"

SRC_URI = "git://git.codelinaro.org/clo/le/platform/vendor/qcom/opensource/arpal-lx.git;protocol=https;rev=0c3dadeb4ba53453b2a143fba6ceb5a2655fede6;branch=audio-core.lnx.1.0.r1-rel;destsuffix=audio/opensource/arpal-lx"

S = "${WORKDIR}/audio/opensource/arpal-lx/inc"

do_compile[noexec] = "1"

ALLOW_EMPTY:${PN} = "1"

