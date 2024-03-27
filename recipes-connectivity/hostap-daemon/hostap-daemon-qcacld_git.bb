inherit pkgconfig logging

include hostap-daemon.inc

PV = "6.0"
PACKAGE_ARCH = "${MACHINE_ARCH}"

FILESEXTRAPATHS:prepend := " ${THISDIR}/files:"

SRC_URI = "git://git.codelinaro.org/clo/le/platform/vendor/qcom-opensource/wpa_supplicant_8.git;protocol=https;rev=df7bfa9ffc9c9444452423ba20601cf821e8e7f1;branch=wlan-os-service.qclinux.1.1.r1-rel;destsuffix=wlan/wpa_supplicant_8"
SRC_URI =+ "file://misc/"

DEPENDS = "pkgconfig libnl openssl"

S = "${WORKDIR}/wlan/wpa_supplicant_8/hostapd"
PATCH_DIR = "${WORKDIR}/wlan/wpa_supplicant_8/"

do_configure() {
        install -m 0644 ${WORKDIR}/misc/defconfig-qcacld .config
        echo "CFLAGS +=\"-I${STAGING_INCDIR}/libnl3\"" >> .config
}
