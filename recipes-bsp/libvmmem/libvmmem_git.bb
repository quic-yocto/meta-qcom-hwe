inherit autotools-brokensep pkgconfig

DESCRIPTION      = "Build Android libvmmem for LE"
LICENSE          = "BSD-3-Clause-Clear"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/${LICENSE};md5=7a434440b651f4a472ca93716d01033a"

SRC_URI += "git://git.codelinaro.org/clo/le/platform/system/memory/libvmmem.git;protocol=https;rev=2b88e9bc6030893b8e9b46ae85999c8de103858d;branch=kernel.apps.lnx.4.0.r1-rel;destsuffix=system/memory/libvmmem"

S = "${WORKDIR}/system/memory/libvmmem"
DEPENDS += "linux-kernel-qcom-headers"

EXTRA_OECONF:append = " \
    --with-sanitized-headers=${STAGING_INCDIR}/linux-kernel-qcom/usr/include \
    --disable-static \
"

PACKAGES +="${PN}-test-bin"

FILES:${PN}     = "${libdir}/* ${sysconfdir}/*"
FILES:${PN}-test-bin = "${base_bindir}/*"

PACKAGE_ARCH = "${MACHINE_ARCH}"
