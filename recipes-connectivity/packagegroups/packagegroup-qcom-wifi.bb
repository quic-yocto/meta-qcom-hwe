SUMMARY = "QCOM WIFI opensource package groups"

LICENSE = "BSD-3-Clause \
           & Qualcomm-Technologies-Inc.-Proprietary \
           "

inherit packagegroup

PROVIDES = "${PACKAGES}"

PACKAGES = "packagegroup-qcom-wifi"

RDEPENDS:packagegroup-qcom-wifi:append = "tcpdump rfkill dnsmasq dhcpcd iperf2 iperf3"

RDEPENDS:packagegroup-qcom-wifi = " \
	qcacld-ini \
	wlan-devicetree \
	wpa-supplicant \
	hostap-daemon-qcacld \
	kernel-module-icnss \
	kernel-module-qcacld-wlan \
        "

