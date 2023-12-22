inherit autotools pkgconfig systemd

DESCRIPTION = "pal"
SECTION = "multimedia"
LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM += "file://Pal.cpp;beginline=30;endline=31;md5=4a19879b8f612e3e0cd49fa53a83dc36 \
                     file://inc/PalDefs.h;beginline=30;endline=31;md5=0f37b80cb1f8d808a27cca9f0cb5e0ac"

SRC_URI = "git://git.codelinaro.org/clo/le/platform/vendor/qcom/opensource/arpal-lx.git;rev=4f44782ac76d533dcbf7efe5089f8ae002f5796d;branch=audio-platform-arintf.lnx.2.0.r3-rel \
            file://0001-pal-pal-patch.patch \
            file://adsprpcd_audiopd.service"

S = "${WORKDIR}/git"

DEPENDS = "tinyalsa tinycompress agm mm-audio-headers audioroute dspservices-headers"

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
