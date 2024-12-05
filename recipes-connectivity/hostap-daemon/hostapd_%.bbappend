PV:qcom-base-bsp = "2.11"

FILESEXTRAPATHS:prepend := "${THISDIR}/${BPN}:"

LIC_FILES_CHKSUM:qcom-base-bsp = "file://hostapd/README;md5=0e430ef1be3d6eebf257cf493fc7661d"

SRC_URI:append:qcom-base-bsp = "file://defconfig.qcom \
				file://0001-UPSTREAM-hostapd-Fix-clearing-up-settings-for-color-switch.patch \
"

SRC_URI[sha256sum] = "2b3facb632fd4f65e32f4bf82a76b4b72c501f995a4f62e330219fe7aed1747a"

do_configure:append:qcom-base-bsp() {
	install -m 0644 ${WORKDIR}/defconfig.qcom ${B}/.config
}

