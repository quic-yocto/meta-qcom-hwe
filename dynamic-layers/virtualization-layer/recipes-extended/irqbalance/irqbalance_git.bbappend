
FILESEXTRAPATHS:prepend:qcom := "${THISDIR}/files:"

SRC_URI += "file://irq.sh \
  file://irqbalance.env \
  file://0001-edit_service.patch \
  "

do_install:append() {
	install -Dm 0644 ${S}/../irq.sh ${D}/usr/sbin/irq.sh
	install -Dm 0644 ${S}/../irqbalance.env ${D}/usr/sbin/irqbalance.env
}

#SYSTEMD_SERVICE:${PN} = "irqbalance.service"

FILES:${PN} += "usr/sbin/irq.sh"
