SUMMARY = "QCOM WIFI opensource package groups"

LICENSE = "BSD-3-Clause \
           & Qualcomm-Technologies-Inc.-Proprietary \
           "

inherit packagegroup

PROVIDES = "${PACKAGES}"

PACKAGES = "${PN}"

RDEPENDS:${PN}:append = "tcpdump rfkill dnsmasq dhcpcd iperf2 iperf3"

RDEPENDS:${PN} = " \
	qcacld-ini \
	wpa-supplicant \
	hostap-daemon-qcacld \
	wlan-sigma-dut \
	wlan-devicetree \
	kernel-module-wlan-platform \
	kernel-module-qcacld-wlan \
        "

PACKAGE_ARCH = "${MACHINE_ARCH}"
