inherit autotools-brokensep pkgconfig

LICENSE = "BSD-3-Clause-Clear"
LIC_FILES_CHKSUM = "file://ar/src/ftm_audio_main.c;beginline=12;endline=14;md5=87eab16c9af6e8f52a3a933a7537d5d9"

DESCRIPTION = "Audio FTM"

SRCPROJECT = "git://git.codelinaro.org/clo/le/platform/vendor/qcom-opensource/audio_ftm.git;protocol=https"
SRCBRANCH  = "audio-core.lnx.1.0.r1-rel"
SRCREV     = "bc4eb5e019994c1564ff649e0eabfe60b621371a"

SRC_URI =  "${SRCPROJECT};branch=${SRCBRANCH};destsuffix=audio/opensource/audio_ftm"

S = "${WORKDIR}/audio/opensource/audio_ftm"

DEPENDS = "tinyalsa glib-2.0 qcom-agm qcom-kvh2xml qcom-args"

EXTRA_OECONF += " --with-glib"

do_install:append:qcm6490() {
    mkdir -p -m 0755 ${D}${sysconfdir}/
    install -m 0644 ${S}/ar/config/qcm6490/* ${D}${sysconfdir}/
}

FILES:${PN}-dbg  = "${libdir}/.debug/* ${bindir}/.debug/*"
FILES:${PN}      = "${libdir}/*.so ${libdir}/*.so.* ${sysconfdir}/* ${bindir}/* ${libdir}/pkgconfig/*"
FILES:${PN}-dev  = "${libdir}/*.la ${includedir}"
