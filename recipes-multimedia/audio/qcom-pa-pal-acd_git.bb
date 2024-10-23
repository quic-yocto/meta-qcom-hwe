inherit autotools pkgconfig

LICENSE = "BSD-3-Clause-Clear"
LIC_FILES_CHKSUM = "file://${QCOM_COMMON_LICENSE_DIR}${LICENSE};md5=3771d4920bd6cdb8cbdf1e8344489ee0"

DESCRIPTION = "ACD UI test app"

SRCPROJECT = "git://git.codelinaro.org/clo/le/platform/vendor/qcom-opensource/pulseaudio-plugin.git;protocol=https"
SRCBRANCH  = "audio-algos.lnx.1.0.r1-rel"
SRCREV     = "47f57bb8df85b134c36e1db65cb1368cfa9cda32"

SRC_URI = "${SRCPROJECT};branch=${SRCBRANCH};destsuffix=audio/opensource/pulseaudio-plugins"

S = "${WORKDIR}/audio/opensource/pulseaudio-plugins/test/acd"

DEPENDS = "pulseaudio glib-2.0 qcom-pal qcom-vui-interface-header"
EXTRA_OECONF = "--with-glib"

do_install:append:qcm6490() {
    mkdir -p -m 0755 ${D}${sysconfdir}/models/acd/
}

SOLIBS = ".so"
FILES_SOLIBSDEV = ""

