SUMMARY = "QCOM WIFI opensource package groups"

LICENSE = "BSD-3-Clause \
           & Qualcomm-Technologies-Inc.-Proprietary"

PACKAGE_ARCH = "${MACHINE_ARCH}"

PROVIDES = "${PACKAGES}"

inherit packagegroup

PACKAGES = "${PN}"

RDEPENDS:${PN}:append = "tcpdump rfkill dnsmasq dhcpcd iperf2 iperf3 nftables"

RDEPENDS:${PN} = " \
    wlan-conf \
    cld80211-lib \
    wpa-supplicant \
    hostap-daemon-qcacld \
    wlan-sigma-dut \
    wlan-devicetree \
    kernel-module-wlan-platform \
    kernel-module-qcacld-wlan \
"

RDEPENDS:${PN}:append = "\
	common-tools \
	ath6kl-utils \
	ftm \
	"
