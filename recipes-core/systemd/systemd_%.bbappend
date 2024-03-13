# Enable coredump support
PACKAGECONFIG:append:qcom = " coredump"

FILESEXTRAPATHS:prepend := "${THISDIR}/${BPN}:"

SRC_URI += " \
   file://0001-unit-introduce-wait-online-.service-for-specific-int.patch \
   file://0002-units-change-Requires-systemd-networkd.service-Binds.patch \
   file://0003-units-Add-CAP_NET_ADMIN-condition-to-systemd-network.patch \
"

# Mask debugfs mount when DEBUG_BUILD is not set
MASK_KERNEL_DEBUG_MOUNT ?= "${@oe.utils.vartrue('DEBUG_BUILD', '0', '', d)}"

do_install:append:qcom () {
     install -d ${D}${systemd_unitdir}/system/multi-user.target.wants
     ln -sf ../systemd-networkd-wait-online@.service \
      ${D}${systemd_unitdir}/system/multi-user.target.wants/systemd-networkd-wait-online@wlan0.service
     ln -sf ../systemd-networkd-wait-online@.service \
      ${D}${systemd_unitdir}/system/systemd-networkd-wait-online.service

     if [ "${MASK_KERNEL_DEBUG_MOUNT}" != "0" ]; then
         ln -sf /dev/null ${D}${sysconfdir}/systemd/system/sys-kernel-debug.mount
     fi
}
