inherit autotools-brokensep linux-kernel-base pkgconfig

DESCRIPTION = "Wi-Fi Protected Access(WPA) Supplicant"
LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/${LICENSE};md5=550794465ba0ec5312d6919e203a55f9"

SOLIBS='*.so'
FILES_SOLIBSDEV=""

INC_PR = "r5"

DEPENDS = "openssl libnl"

SRC_URI = "file://${WORKSPACE}/external/hostap"

S = "${WORKDIR}/hostap/wpa_supplicant"

do_install() {
    if [ ${BASEMACHINE} == "sdxlemur" ]; then
        make install DESTDIR=${D} BINDIR=${sbindir}/mcc LIBDIR=${libdir} INCDIR=${includedir}
        ln -sf /systemrw/wlan/bin/wpa_supplicant ${D}/usr/sbin/wpa_supplicant
        ln -sf /systemrw/wlan/bin/wpa_cli ${D}/usr/sbin/wpa_cli
    else
        make install DESTDIR=${D} BINDIR=${sbindir} LIBDIR=${libdir} INCDIR=${includedir}
    fi
}
