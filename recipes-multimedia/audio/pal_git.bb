inherit autotools pkgconfig systemd

DESCRIPTION = "pal"
SECTION = "multimedia"
LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM += "file://Pal.cpp;beginline=31;endline=32;md5=e733afaf233fbcbc22769d0a9bda0b3e \
                     file://inc/PalDefs.h;beginline=30;endline=31;md5=e733afaf233fbcbc22769d0a9bda0b3e"

SRC_URI  = "git://git.codelinaro.org/clo/le/platform/vendor/qcom/opensource/arpal-lx.git;protocol=https;rev=dd84b4936e9961807ea1060aaa4461f4a6ff2138;branch=audio-platform-arintf.lnx.2.0.r3-rel;destsuffix=audio/opensource/arpal-lx \
            file://0001-pal-pal-patch.patch \
            file://adsprpcd_audiopd.service"

S = "${WORKDIR}/audio/opensource/arpal-lx"

DEPENDS = "tinyalsa tinycompress agm mm-audio-headers audioroute dspservices-headers vui-interface pal-headers"

EXTRA_OECONF += "--with-glib --with-syslog"

SYSTEMD_SERVICE:${PN} += "adsprpcd_audiopd.service"

do_install:append () {
    install -d ${D}${systemd_system_unitdir}
    install -m 0644 ${WORKDIR}/adsprpcd_audiopd.service ${D}${systemd_system_unitdir}
    install -d ${D}${systemd_system_unitdir}/multi-user.target.wants/
    ln -sf ${systemd_system_unitdir}/adsprpcd_audiopd.service \
           ${D}${systemd_system_unitdir}/multi-user.target.wants/adsprpcd_audiopd.service
}

FILES:${PN} += "${libdir}/*.so ${libdir}/pkgconfig/ ${systemd_unitdir}/system/* ${sysconfdir}/* ${bindir}/*"
FILES:${PN}-dev = "${libdir}/*.la ${includedir}"
