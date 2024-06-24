inherit autotools pkgconfig

DESCRIPTION = "vui-interface header"

LICENSE = "BSD-3-Clause-Clear"
LIC_FILES_CHKSUM += "file://VoiceUIInterface.h;beginline=9;endline=11;md5=6e81219b9da09d37472d59042b06a623"

SRC_URI = "git://git.codelinaro.org/clo/le/platform/vendor/qcom/opensource/arpal-lx.git;protocol=https;rev=b747b9fd0881fd9cb3d7a6efd6f33bb667466870;branch=audio-core.lnx.1.0.r1-rel;destsuffix=audio/opensource/arpal-lx"

S = "${WORKDIR}/audio/opensource/arpal-lx/api/vui-interface"

do_compile[noexec] = "1"

ALLOW_EMPTY:${PN} = "1"
