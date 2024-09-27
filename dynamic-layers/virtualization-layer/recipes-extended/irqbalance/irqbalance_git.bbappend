
FILESEXTRAPATHS:prepend:qcom := "${THISDIR}/files:"

SRC_URI:append:qcom = " \
    file://irq.sh \
    file://irqbalance.env \
    file://0001-edit_service.patch \
    file://0001-irqbalance-Add-Stop-timeout-of-20-seconds-for-irqbal.patch \
    "

do_install:append:qcom() {
	install -Dm 0755 ${S}/../irq.sh ${D}/usr/sbin/irq.sh
	install -Dm 0644 ${S}/../irqbalance.env ${D}/usr/sbin/irqbalance.env
}

FILES:${PN} += "usr/sbin/irq.sh"
