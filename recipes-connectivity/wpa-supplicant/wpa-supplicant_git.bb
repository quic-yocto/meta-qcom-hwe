inherit pkgconfig logging
include wpa-supplicant.inc

PV = "6.0"
PACKAGE_ARCH = "${MACHINE_ARCH}"

FILESEXTRAPATHS:prepend := " ${THISDIR}/files:"

SRC_URI = "git://git.codelinaro.org/clo/le/platform/vendor/qcom-opensource/wpa_supplicant_8.git;protocol=https;rev=56a926a45edad8e7c6ee628f0aeee5876b9d50d4;branch=wlan-os-service.qclinux.1.1.r1-rel;destsuffix=wlan/wpa_supplicant_8"
SRC_URI += "file://misc/"

DEPENDS += "glib-2.0 dbus"

S = "${WORKDIR}/wlan/wpa_supplicant_8/wpa_supplicant"

PREFERRED_PROVIDER_wpa-supplicant = "wpa-supplicant-qcacld"

do_configure() {
        install -m 0644 ${WORKDIR}/misc/defconfig-qcacld .config
        echo "CFLAGS +=\"-I${STAGING_INCDIR}/libnl3\"" >> .config
}

FILES:${PN} += "/usr/include/*"
