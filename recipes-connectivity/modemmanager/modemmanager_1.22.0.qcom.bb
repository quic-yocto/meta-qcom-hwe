SUMMARY = "ModemManager is a daemon controlling broadband devices/connections"
DESCRIPTION = "ModemManager is a DBus-activated daemon which controls mobile broadband (2G/3G/4G) devices and connections"

LICENSE = "GPL-2.0-or-later & LGPL-2.1-or-later"
LIC_FILES_CHKSUM = " \
    file://COPYING;md5=b234ee4d69f5fce4486a80fdaf4a4263 \
    file://COPYING.LIB;md5=4fbd65380cdd255951079008b364516c \
"

DEFAULT_PREFERENCE = "-1"

GNOMEBASEBUILDCLASS = "meson"
inherit gnomebase gettext systemd gobject-introspection bash-completion

DEPENDS = "glib-2.0 libgudev libxslt-native dbus json-glib  gtk+3"


FILESEXTRAPATHS:append := "${THISDIR}:"
FILESEXTRAPATHS:append := "${THISDIR}/patches:"

SRCPROJECT = "git://git.codelinaro.org/clo/le/mobile-broadband/ModemManager.git;protocol=https"
SRCBRANCH  = "telephony.qclinux.0.0.r1-rel"
SRCREV     = "aabbd7ad498c0a049cf84e55596f8d01a1dafb89"

SRC_URI = "${SRCPROJECT};branch=${SRCBRANCH};destsuffix=mobile-broadband/ModemManager \
           file://0001-Increase-delay-for-probing.patch"

S = "${WORKDIR}/mobile-broadband/ModemManager"

#RDEPENDS:${PN} += " bash"
EXTRA_OECONF:append = " --enable-plugin-qcom-soc"

# strict, permissive
MODEMMANAGER_POLKIT_TYPE ??= "permissive"

PACKAGECONFIG ??= "vala qmi qrtr \
    ${@bb.utils.filter('DISTRO_FEATURES', 'systemd polkit', d)} \
"

PACKAGECONFIG[at] = "-Dat_command_via_dbus=true"
PACKAGECONFIG[systemd] = " \
    -Dsystemdsystemunitdir=${systemd_unitdir}/system/, \
    -Dsystemdsystemunitdir=no -Dsystemd_journal=false -Dsystemd_suspend_resume=false \
"
PACKAGECONFIG[polkit] = "-Dpolkit=${MODEMMANAGER_POLKIT_TYPE},-Dpolkit=no,polkit"
# Support WWAN modems and devices which speak the Mobile Interface Broadband Model (MBIM) protocol.
PACKAGECONFIG[mbim] = "-Dmbim=true,-Dmbim=false -Dplugin_dell=disabled -Dplugin_foxconn=disabled,libmbim"
# Support WWAN modems and devices which speak the Qualcomm MSM Interface (QMI) protocol.
PACKAGECONFIG[qmi] = "-Dqmi=true,-Dqmi=false,libqmi"
PACKAGECONFIG[qrtr] = "-Dqrtr=true,-Dqrtr=false,libqrtr-glib"
PACKAGECONFIG[vala] = "-Dvapi=true,-Dvapi=false"

inherit ${@bb.utils.contains('PACKAGECONFIG', 'vala', 'vala', '', d)}

EXTRA_OEMESON = " \
    -Dudevdir=${nonarch_base_libdir}/udev \
    -Dqrtr=false \
"

FILES:${PN} += " \
    ${datadir}/icons \
    ${datadir}/polkit-1 \
    ${datadir}/dbus-1 \
    ${datadir}/ModemManager \
    ${libdir}/ModemManager \
    ${systemd_unitdir}/system \
"

SYSTEMD_SERVICE:${PN} = "ModemManager.service"
