inherit pkgconfig logging
include wpa-supplicant.inc

PR = "${INC_PR}.2"
PV = "6.0"
PACKAGE_ARCH = "${MACHINE_ARCH}"

SRC_URI = "git://git.codelinaro.org/clo/le/platform/vendor/qcom-opensource/wpa_supplicant_8.git;protcol=https;rev=afe22e3b9bb33451cd252a5617e1d9aa42841e42;branch=wlan-os-service.qclinux.1.1.r1-rel"

FILESEXTRAPATHS:prepend := " ${THISDIR}/files:"
SRC_URI =+ "file://misc/"

DEPENDS += "glib-2.0 dbus"

FILES:${PN} += "/usr/include/*"

S = "${WORKDIR}/git/wpa_supplicant"

PREFERRED_PROVIDER_wpa-supplicant = "wpa-supplicant-qcacld"

do_configure() {
        install -m 0644 ${WORKDIR}/misc/defconfig-qcacld .config
        echo "CFLAGS +=\"-I${STAGING_INCDIR}/libnl3\"" >> .config
}
