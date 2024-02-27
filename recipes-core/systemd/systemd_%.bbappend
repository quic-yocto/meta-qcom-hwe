# Enable coredump support
PACKAGECONFIG:append:qcom = " coredump"

FILESEXTRAPATHS:prepend := "${THISDIR}/${BPN}:"

SRC_URI += " \
   file://0001-unit-introduce-wait-online-.service-for-specific-int.patch \
   file://0002-units-change-Requires-systemd-networkd.service-Binds.patch \
   file://0003-units-Add-CAP_NET_ADMIN-condition-to-systemd-network.patch \
"

do_install:append() {
        install -d ${D}${systemd_unitdir}/system/multi-user.target.wants
        ln -sf ../systemd-networkd-wait-online@.service \
      ${D}${systemd_unitdir}/system/multi-user.target.wants/systemd-networkd-wait-online@wlan0.service
        ln -sf ../systemd-networkd-wait-online@.service \
      ${D}${systemd_unitdir}/system/systemd-networkd-wait-online.service
}
