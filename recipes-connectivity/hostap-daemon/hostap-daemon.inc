inherit autotools-brokensep linux-kernel-base pkgconfig

DESCRIPTION = "Hostap Daemon"
LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/${LICENSE};md5=550794465ba0ec5312d6919e203a55f9"

FILES:${PN} += "\
        /usr/bin \
        "
INC_PR = "r4"

DEPENDS = "openssl libnl"

SRC_URI = "file://${WORKSPACE}/external/hostap/"

S = "${WORKDIR}/external/hostap/hostapd"

do_install() {
    if [ ${BASEMACHINE} == "sdxlemur" ]; then
        make install DESTDIR=${D} BINDIR=${sbindir}/mcc/
        ln -sf /systemrw/wlan/bin/hostapd ${D}/usr/sbin/hostapd
        ln -sf /systemrw/wlan/bin/hostapd_cli ${D}/usr/sbin/hostapd_cli
    else
        make install DESTDIR=${D} BINDIR=${sbindir}/
    fi
}
