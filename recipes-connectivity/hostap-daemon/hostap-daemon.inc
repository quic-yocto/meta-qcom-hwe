inherit autotools-brokensep linux-kernel-base pkgconfig

DESCRIPTION = "Hostap Daemon"
LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/${LICENSE};md5=550794465ba0ec5312d6919e203a55f9"

FILES:${PN} += "/usr/bin"

DEPENDS = "openssl libnl"

do_install() {
	make install DESTDIR=${D} BINDIR=${sbindir}/
}
