inherit pkgconfig

include hostap-daemon.inc

LICENSE = "BSD-3-Clause"

PV = "6.0.qcom"
PACKAGE_ARCH = "${SOC_ARCH}"

FILESEXTRAPATHS:prepend := " ${THISDIR}/files:"

SRC_URI = "git://w1.fi/hostap.git;protocol=https;branch=main"
SRC_URI += "file://misc/"
SRC_URI += "file://patch/0001-UPSTREAM-hostapd-Fix-clearing-up-settings-for-color-switch.patch;patchdir=${WORKDIR}/git/"

SRCREV = "9716bf1160beb677e965d9e6475d6c9e162e8374"

DEPENDS = "pkgconfig libnl openssl"

S = "${WORKDIR}/git//hostapd"
PATCH_DIR = "${WORKDIR}/git/"

do_configure() {
        install -m 0644 ${WORKDIR}/misc/defconfig .config
        echo "CFLAGS +=\"-I${STAGING_INCDIR}/libnl3\"" >> .config
}
