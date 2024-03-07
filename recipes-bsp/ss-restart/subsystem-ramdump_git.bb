inherit autotools pkgconfig systemd

DESCRIPTION = "subsystem ramdump"
LICENSE = "BSD-3-Clause-Clear"
LIC_FILES_CHKSUM += "file://${COMMON_LICENSE_DIR}/BSD-3-Clause-Clear;md5=7a434440b651f4a472ca93716d01033a"

SRC_URI += "git://git.codelinaro.org/clo/le/platform/vendor/qcom/opensource/ss-restart.git;protocol=https;rev=74c35bdb8c9f28fbc72da5e5168d97401d5548a6;branch=kernel.apps.lnx.4.0.r1-rel"

S = "${WORKDIR}/git/subsystem_ramdump"

DEPENDS = "glib-2.0"

EXTRA_OECONF = " --with-glib"

do_install:append() {
    install -Dm 0644 ${S}/subsystem-ramdump.service ${D}${systemd_unitdir}/system/subsystem-ramdump.service
}

SYSTEMD_SERVICE:${PN} = "subsystem-ramdump.service"

FILES:${PN} += "${systemd_unitdir}/system/*"
