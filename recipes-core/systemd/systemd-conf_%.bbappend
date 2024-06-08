FILESEXTRAPATHS:prepend := "${THISDIR}/${BPN}:"

# logind.conf
do_install:append:qcom() {
   # Ignore PowerKey
   sed -i '$aHandlePowerKey=ignore' ${D}${systemd_unitdir}/logind.conf.d/00-${PN}.conf
}
