inherit autotools pkgconfig

LICENSE = "BSD-3-Clause-Clear"
LIC_FILES_CHKSUM = "file://sva_intf/src/SVAInterface.cpp;beginline=31;endline=32;md5=e733afaf233fbcbc22769d0a9bda0b3e"

DESCRIPTION = "VUI Interface plugin"

SRCPROJECT = "git://git.codelinaro.org/clo/le/platform/vendor/qcom/opensource/arpal-lx.git;protocol=https"
SRCBRANCH  = "audio-core.lnx.1.0.r1-rel"
SRCREV     = "9254849d3dc20f1c84dbc1af99ba5c37bd9188e3"

SRC_URI    =  "${SRCPROJECT};branch=${SRCBRANCH};destsuffix=audio/opensource/arpal-lx"

S = "${WORKDIR}/audio/opensource/arpal-lx/plugins/vui_interface"

DEPENDS = "qcom-pal-headers qcom-kvh2xml qcom-vui-interface-header qcom-args"

EXTRA_OECONF += " --with-glib"

SOLIBS = ".so"
FILES_SOLIBSDEV = ""

