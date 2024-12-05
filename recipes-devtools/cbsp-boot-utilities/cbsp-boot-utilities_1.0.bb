inherit autotools pkgconfig systemd

DESCRIPTION = "Uefi sec app tool"
SUMMARY = "Uefi sec app tool is used for capsule update"
LICENSE = "BSD-3-Clause-Clear"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/${LICENSE};md5=7a434440b651f4a472ca93716d01033a"

SRCREV = "${AUTOREV}"
SRC_URI = "git://github.com/quic/cbsp-boot-utilities;protocol=https;branch=main"

S = "${WORKDIR}/git/uefi_sec"

DEPENDS += "securemsm syslog-plumber"

AUTOTOOLS_SCRIPT_PATH = "${S}"

PACKAGECONFIG ??= "${@bb.utils.filter('DISTRO_FEATURES', 'systemd', d)}"
PACKAGECONFIG[systemd] = "--with-systemdunitdir=${systemd_system_unitdir}/,--with-systemdunitdir="

SYSTEMD_SERVICE:${PN} = "uefi_sec.service"
