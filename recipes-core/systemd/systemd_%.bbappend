# Enable coredump support
PACKAGECONFIG:append:qcom = " coredump"

FILESEXTRAPATHS:prepend := "${THISDIR}/${BPN}:"

SRC_URI:append:qcom = " \
   file://0001-unit-introduce-wait-online-.service-for-specific-int.patch \
   file://0002-units-change-Requires-systemd-networkd.service-Binds.patch \
   file://0003-units-Add-CAP_NET_ADMIN-condition-to-systemd-network.patch \
"

PACKAGECONFIG:append:qcom = " efi"
PACKAGECONFIG:append:qcom = " gnu-efi"

TARGET_CC_ARCH += "${LDFLAGS}"

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

     # Run-time time creation  of /var/lib/systemd/backlight directory is
     # already handled from systemd-backlight@.service. Hence, drop
     # installing /var/lib/systemd/backlight at build-time.
     if ${@bb.utils.contains('PACKAGECONFIG', 'backlight', 'true', 'false', d)}; then
         if [ -d "${D}${localstatedir}/lib/systemd/backlight" ]; then
             (cd ${D}${localstatedir}; rmdir -v --parents lib/systemd/backlight)
         fi
     fi

     # Run-time time creation of /var/log/journal directory is already
     # already handled from systemd-journald@.service. Hence, drop
     # installing /var/log/journal at build-time.
     if [-d "${D}${localstatedir}/log/journal"]; then
          rm -rf ${D}${localstatedir}/log/
         (cd ${D}${localstatedir}; rmdir -v --parents log/journal)
     fi
}
