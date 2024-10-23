inherit autotools pkgconfig systemd

DESCRIPTION = "subsystem ramdump"
LICENSE = "BSD-3-Clause-Clear"
LIC_FILES_CHKSUM += "file://subsystem_ramdump.c;beginline=1;endline=4;md5=e19ba7a648e00191388321a1e8a5c974"

SRCPROJECT = "git://git.codelinaro.org/clo/le/platform/vendor/qcom/opensource/ss-restart.git;protocol=https"
SRCBRANCH  = "kernel.apps.lnx.4.0.r1-rel"
SRCREV     = "4541e993a9f8ee40cfdea2521ac3621ceadb2f00"

SRC_URI = "${SRCPROJECT};branch=${SRCBRANCH};destsuffix=ss-restart"

S = "${WORKDIR}/ss-restart/subsystem_ramdump"

DEPENDS = "glib-2.0"

EXTRA_OECONF = " --with-glib"

do_install:append() {
    install -Dm 0644 ${S}/subsystem-ramdump.service ${D}${systemd_unitdir}/system/subsystem-ramdump.service
}

SYSTEMD_SERVICE:${PN} = "subsystem-ramdump.service"

FILES:${PN} += "${systemd_unitdir}/system/*"
