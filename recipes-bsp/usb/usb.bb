inherit systemd

SUMMARY = "QCOM USB configuration script"

LICENSE = "BSD-3-Clause-Clear"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/${LICENSE};md5=7a434440b651f4a472ca93716d01033a"

SRC_URI = "file://usb.service \
           file://qusb"

do_install() {
	install -d ${D}${bindir}
	install -m 0755 ${WORKDIR}/qusb ${D}${bindir}
	install -m 0644 ${WORKDIR}/usb.service -D ${D}${systemd_unitdir}/system/usb.service
}

FILES:${PN} += "${systemd_unitdir}/system/ \
		${bindir} "

SYSTEMD_SERVICE_${PN} = "usb.service"
