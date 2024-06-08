SUMMARY = "QCOM WIFI opensource package groups"

LICENSE = "BSD-3-Clause \
           & Qualcomm-Technologies-Inc.-Proprietary"

PACKAGE_ARCH = "${MACHINE_ARCH}"

PROVIDES = "${PACKAGES}"

inherit packagegroup

PACKAGES = "${PN}"

RDEPENDS:${PN}:append = "tcpdump rfkill dnsmasq dhcpcd iperf2 iperf3 nftables iputils"

RDEPENDS:${PN} = " \
    wlan-conf \
    cld80211-lib \
    wpa-supplicant \
    hostap-daemon-qcacld \
    wlan-sigma-dut \
    wlan-devicetree \
    kernel-module-wlan-platform \
    kernel-module-qcacld-wlan \
    common-tools \
    ath6kl-utils \
    ftm \
"
# qcs9100 support is not available.
RDEPENDS:${PN}:remove:qcs9100 = "wlan-devicetree"
