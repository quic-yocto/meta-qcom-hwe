inherit systemd

SUMMARY = "QCOM USB configuration script"

LICENSE = "BSD-3-Clause-Clear"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/${LICENSE};md5=7a434440b651f4a472ca93716d01033a"

SRC_URI = "file://usb.service \
           file://qusb \
	   file://usb_bind.rules \
	   file://var-usbfw.mount"

do_install() {
	install -d ${D}${bindir}
	install -m 0755 ${WORKDIR}/qusb ${D}${bindir}
	install -m 0644 ${WORKDIR}/usb.service -D ${D}${systemd_unitdir}/system/usb.service
	install -d ${D}${sysconfdir}/udev/rules.d/
	install -m 0644 ${WORKDIR}/usb_bind.rules ${D}${sysconfdir}/udev/rules.d/
}

do_install:append:qcm6490 () {
	install -d ${D}${systemd_unitdir}/system/local-fs.target.wants
	install -m 0644 ${WORKDIR}/var-usbfw.mount ${D}${systemd_unitdir}/system/var-usbfw.mount
	install -d ${D}${nonarch_base_libdir}/firmware/
	ln -sf ${systemd_unitdir}/system/var-usbfw.mount ${D}${systemd_unitdir}/system/local-fs.target.wants/var-usbfw.mount
	ln -sf /var/usbfw/renesas_usb_fw.mem ${D}${nonarch_base_libdir}/firmware/renesas_usb_fw.mem
}

FILES:${PN} += "${systemd_unitdir}/system/ \
		${bindir} \
		${nonarch_base_libdir}/udev/rules.d/usb_bind.rules"

FILES:${PN}:append:qcm6490 = " ${systemd_unitdir}/system/* \
				${nonarch_base_libdir}/firmware/*"

SYSTEMD_SERVICE_${PN} = "usb.service"
