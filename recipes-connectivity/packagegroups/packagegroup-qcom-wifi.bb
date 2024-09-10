SUMMARY = "QCOM WIFI opensource package groups"

LICENSE = "BSD-3-Clause \
           & Qualcomm-Technologies-Inc.-Proprietary"

PACKAGE_ARCH = "${MACHINE_ARCH}"

inherit packagegroup

PROVIDES = "${PACKAGES}"

PACKAGES = "${PN}"

RDEPENDS:${PN} = " \
    wpa-supplicant \
"

RDEPENDS:${PN}:append:qcom-custom-bsp = " \
    wlan-conf \
    cld80211-lib \
    hostap-daemon \
    wlan-sigma-dut \
"

RDEPENDS:${PN}:append:qcom-base-bsp = " \
    hostapd \
"

RDEPENDS:${PN}:append = "tcpdump rfkill dnsmasq dhcpcd iperf2 iperf3 nftables iputils trace-cmd"


RDEPENDS:${PN}:append:qcom-custsom-bsp = "\
	qcom-ath6kl-utils \
	qcom-ftm \
	qcom-wlan-tools \
	"
