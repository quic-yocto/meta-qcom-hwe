inherit systemd

FILESEXTRAPATHS:prepend := "${THISDIR}/${BPN}:"


RRECOMMENDS:${PN} += " \
    glibc-gconv-utf-16 \
    glibc-gconv-utf-32 \
"

SRC_URI:append:qcom = " file://0001-Setting-default-values-in-main.conf.patch \
                        file://0002-Use-system-bus-instead-of-session-for-obexd.patch \
                        file://0003-Bluez-unregister-includes-option-not-working.patch \
                        file://0004-gen_metadata-used-to-test-metadata-over-avrcp.patch \
                        file://0005-Set-variable-MAP_ROOT-map-messages-to-create-MAP-sdp.patch \
                        file://0006-obex-Move-size-emit-signal-to-plugins-instead-of-obe.patch \
                        file://0007-Fix-discoverable-property-not-emitted-on-updating-va.patch \
                        file://0008-Set-BREDR-not-supported-bit-in-AD-Flag-when-discover.patch \
                        file://0009-advertising-Do-not-crash-in-case-of-adv-update-failu.patch \
                        file://0010-device-Add-btd_device_bearer_is_connected.patch \
                        file://0011-device-Fix-marking-device-as-temporary.patch \
                        file://0012-advertising-Fix-peripheral-adverts-when-Discoverable.patch \
                        file://0013-adapter-Cancel-the-service-authorization-when-remote.patch \
                        file://0014-Manage-device-state-of-cross-transport-SMP-keys.patch \
                        file://0015-client-gatt-Fix-scan-build-warning.patch \
                        file://0016-client-Allow-gatt.select-attribute-to-work-with-local-attributes.patch \
                        file://qca_set_bdaddr.service \
                        file://qca_set_bdaddr.sh \
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

    #Create below directory which is used by obex service
    install -d ${D}${localstatedir}/bluetooth

    # Install script to set unique BDA
    install -d ${D}${sysconfdir}/initscripts
    install -m 755 ${WORKDIR}/qca_set_bdaddr.sh ${D}${sysconfdir}/initscripts/

    # Install service that will run qca_set_bdaddr.sh script on boot
    install -m 0644 ${WORKDIR}/qca_set_bdaddr.service -D ${D}${systemd_system_unitdir}/qca_set_bdaddr.service
    install -d ${D}${systemd_system_unitdir}/bluetooth.target.wants/
    ln -sf ${systemd_system_unitdir}/qca_set_bdaddr.service ${D}${systemd_system_unitdir}/bluetooth.target.wants/qca_set_bdaddr.service
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

SYSTEMD_SERVICE:${PN} += "qca_set_bdaddr.service"
FILES:${PN}:append = "${systemd_system_unitdir}/qca_set_bdaddr.service \
                           ${sysconfdir}/initscripts/qca_set_bdaddr.sh \
"
