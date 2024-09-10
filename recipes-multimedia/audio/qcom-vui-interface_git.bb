inherit autotools pkgconfig

LICENSE = "BSD-3-Clause-Clear"
LIC_FILES_CHKSUM = "file://src/VoiceUIInterface.cpp;beginline=2;endline=4;md5=18c068bf29acd853c35b2114ff818b13\
                    file://inc/SVAExtension.h;beginline=2;endline=4;md5=5c4a16422d3dd48ea3e869f02b4e4668"

DESCRIPTION = "VUI Interface plugin"

SRC_URI    =  "git://git.codelinaro.org/clo/le/platform/vendor/qcom/opensource/arpal-lx.git;protocol=https;rev=0c3dadeb4ba53453b2a143fba6ceb5a2655fede6;branch=audio-core.lnx.1.0.r1-rel;destsuffix=audio/opensource/arpal-lx"

S = "${WORKDIR}/audio/opensource/arpal-lx/plugins/vui_interface"

DEPENDS = "qcom-pal-headers qcom-kvh2xml qcom-vui-interface-header qcom-audiolisten qcom-args"

EXTRA_OECONF += " --with-glib"

SOLIBS = ".so"
FILES_SOLIBSDEV = ""

