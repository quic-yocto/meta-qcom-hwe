inherit autotools-brokensep pkgconfig

DESCRIPTION = "Build Android libdmabufheap for LE"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/${LICENSE};md5=89aea4e17d99a7cacdbeed46a0096b10"

DEPENDS:qcom-custom-bsp += "linux-kernel-qcom-headers"

SRCPROJECT = "git://git.codelinaro.org/clo/le/platform/system/memory/libdmabufheap.git;protocol=https"
SRCBRANCH  = "memory-le-apps.lnx.1.0.r35-rel"
SRCREV     = "37bf33001a41b66676d6d1883982f0b14583fc02"

SRC_URI   = " \
             ${SRCPROJECT};branch=${SRCBRANCH};destsuffix=system/memory/libdmabufheap \
             file://kmem.rules \
             "

S = "${WORKDIR}/system/memory/libdmabufheap"

EXTRA_OECONF:append = " \
    --disable-static \
    --with-sanitized-headers=${STAGING_INCDIR}/linux/usr/include \
"

do_install:append() {
    install -m 0644 ${WORKDIR}/kmem.rules -D ${D}${sysconfdir}/udev/rules.d/kmem.rules
}

PACKAGES +="${PN}-test-bin"

PACKAGECONFIG[ion] = "--with-ion, --without-ion, libion"

FILES:${PN}     = "${libdir}/pkgconfig/* ${libdir}/* ${sysconfdir}/*"
FILES:${PN}-test-bin = "${base_bindir}/*"

PACKAGE_ARCH = "${SOC_ARCH}"
