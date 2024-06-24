inherit update-rc.d systemd pkgconfig

FILESEXTRAPATHS:prepend := "${THISDIR}/${BPN}:"


RRECOMMENDS:${PN} += " \
    glibc-gconv-utf-16 \
    glibc-gconv-utf-32 \
"

SRC_URI:append:qcom = " file://0001-Setting-default-values-in-main.conf.patch \
                        file://0002-Use-system-bus-instead-of-session-for-obexd.patch \
                        file://0003-Bluez-unregister-includes-option-not-working.patch \
"

SRC_URI:append:qcom:qcs9100 = " file://load_bluetooth_module \
                        file://load_bluetooth_module.service \
"

#Include obex to support obex related profiles like OPP, FTP, MAP, PBAP
RDEPENDS:${PN} += "${PN}-obex"

#Include only desired tools that are conditional on READLINE in bluez
INST_TOOLS_READLINE = " \
    tools/bluetooth-player \
    tools/obexctl \
    tools/btmgmt \
"

#Remove desired tools from noinst-tools
NOINST_TOOLS:remove = " \
    ${@bb.utils.contains('PACKAGECONFIG', 'readline', '${INST_TOOLS_READLINE}', '', d)} \
"

do_install:append:qcom() {
    install -v -m 0644  ${S}/src/main.conf ${D}${sysconfdir}/bluetooth/

    #Install desired tools that upstream leaves in build area
    for f in ${INST_TOOLS_READLINE} ; do
        install -m 755 ${B}/$f ${D}/${bindir}
    done

    install -m 0644 ${B}/obexd/src/obex.service ${D}${systemd_system_unitdir}/obex.service

}

do_install:append:qcs9100() {
  install -d ${D}${sysconfdir}/modprobe.d
  echo "blacklist hci_uart" > ${D}${sysconfdir}/modprobe.d/blacklist.conf

  install -d ${D}${sysconfdir}/initscripts
  install -d ${D}${systemd_unitdir}/system/multi-user.target.wants/
  install -m 0755 ${WORKDIR}/load_bluetooth_module ${D}${sysconfdir}/initscripts

  install -m 0644 ${WORKDIR}/load_bluetooth_module.service -D ${D}${systemd_unitdir}/system/load_bluetooth_module.service
  ln -sf ${systemd_unitdir}/system/load_bluetooth_module.service ${D}${systemd_unitdir}/system/multi-user.target.wants/load_bluetooth_module.service
}

SYSTEMD_PACKAGES += "${PN}-obex"
SYSTEMD_SERVICE:${PN}-obex += "obex.service"

SYSTEMD_SERVICE:${PN}:qcs9100 += "load_bluetooth_module.service"
FILES:${PN}:append:qcs9100 = "${systemd_unitdir}/load_bluetooth_module.service \
                              ${sysconfdir}/initscripts/load_bluetooth_module \
"
