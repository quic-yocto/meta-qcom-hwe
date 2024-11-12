inherit pkgconfig
include wpa-supplicant.inc

DEFAULT_PREFERENCE = "-1"

FILESEXTRAPATHS:prepend := " ${THISDIR}/files:"

SRC_URI = "git://w1.fi/hostap.git;protocol=https;branch=main"
SRC_URI += "file://misc/"
SRC_URI += "file://0001-WNM-Extend-workaround-for-broken-AP-operating-class-behavior.patch;patchdir=${WORKDIR}/git/"
SRC_URI += "file://0001-Use-helper-functions-to-access-RSNE-RSNXE-from-BSS-e.patch;patchdir=${WORKDIR}/git/"
SRC_URI += "file://0001-FT-Do-not-omit-RSNXE-from-FT-initial-mobility-domain.patch;patchdir=${WORKDIR}/git/"

SRCREV = "9716bf1160beb677e965d9e6475d6c9e162e8374"

DEPENDS += "glib-2.0 dbus"

S = "${WORKDIR}/git/wpa_supplicant"

do_configure() {
        install -m 0644 ${WORKDIR}/misc/defconfig .config
        echo "CFLAGS +=\"-I${STAGING_INCDIR}/libnl3\"" >> .config
}

FILES:${PN} += "/usr/include/*"
