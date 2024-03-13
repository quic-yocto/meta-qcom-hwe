inherit autotools-brokensep linux-kernel-base pkgconfig

DESCRIPTION = "Wi-Fi Protected Access(WPA) Supplicant"
LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/${LICENSE};md5=550794465ba0ec5312d6919e203a55f9"

SOLIBS='*.so'
FILES_SOLIBSDEV=""

DEPENDS = "openssl libnl"

do_install() {
	make install DESTDIR=${D} BINDIR=${sbindir} LIBDIR=${libdir} INCDIR=${includedir}
}
