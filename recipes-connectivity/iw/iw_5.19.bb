DESCRIPTION = "cfg80211 interface configuration utility"
LICENSE = "ISC"
LIC_FILES_CHKSUM = "file://COPYING;md5=878618a5c4af25e9b93ef0be1a93f774"

PV = "5.19"
SRC_URI = "https://www.kernel.org/pub/software/network/iw/iw-${PV}.tar.xz \
	   "
SRC_URI[md5sum] = "fd17ca2dd5f160a5d9e5fd3f8a69f416"
SRC_URI[sha256sum] = "f167bbe947dd53bb9ebc0c1dcef5db6ad73ac1d6084f2c6f9376c5c360cc4d4e"

DEPENDS = "libnl"
S = "${WORKDIR}/iw-${PV}"

TARGET_CFLAGS += "-fpie -Wall -Werror -Wno-error=sign-compare"
TARGET_LDFLAGS += "-pie -L${STAGING_LIBDIR}"
TARGET_CPPFLAGS = "-I${STAGING_INCDIR}/libnl3 \
		   ${TAGET_CPPFLAGS} \
		   -DCONFIG_LIBNL20 \
		   -D_GNU_SOURCE"

inherit pkgconfig

do_configure() {
	echo "const char iw_version[] = \"${PV}\";" > ${S}/version.c
	rm -f ${S}/version.sh
	touch ${S}/version.sh
	chmod +x ${S}/version.sh
}

do_compile:prepend() {
	CFLAGS="${TARGET_CPPFLAGS} ${TARGET_CFLAGS} -ffunction-sections -fdata-sections" \
	LDFLAGS="${TARGET_LDFLAGS} -Wl,--gc-sections" \
	export NL1FOUND="" \
	export NL2FOUND="" \
	export NL3FOUND="" \
	export NL31FOUND="" \
	export NL3xFOUND="Y" \
	export NLLIBNAME="libnl3" \
	LIBS="-lm -lnl-3" \
	export V=1
}

do_install() {
	install -d ${D}/usr/sbin
	install -m 0755 ${S}/iw ${D}/usr/sbin/
}

FILES:${PN} += "/usr/sbin/*"
