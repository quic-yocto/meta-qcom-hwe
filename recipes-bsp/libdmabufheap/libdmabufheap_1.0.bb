inherit autotools-brokensep pkgconfig

DESCRIPTION = "Build Android libdmabufheap for LE"
HOMEPAGE = "http://developer.android.com/"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/files/common-licenses/\
${LICENSE};md5=89aea4e17d99a7cacdbeed46a0096b10"


SRC_URI = "git://git.codelinaro.org/clo/le/platform/system/memory/libdmabufheap;protocol=https;branch=memory-le-apps.lnx.1.0.r32-rel;rev=ab7c9981f56100691194a63fb6e4129ba7b9917c"

S = "${WORKDIR}/git"
DEPENDS += "linux-kernel-qcom-headers"

EXTRA_OECONF:append = " \
    --disable-static \
    --with-sanitized-headers=${STAGING_INCDIR}/linux/usr/include \
"

PACKAGES +="${PN}-test-bin"

PACKAGECONFIG[ion] = "--with-ion, --without-ion, libion"

FILES:${PN}     = "${libdir}/pkgconfig/* ${libdir}/* ${sysconfdir}/*"
FILES:${PN}-test-bin = "${base_bindir}/*"

PACKAGE_ARCH = "${MACHINE_ARCH}"
