inherit autotools-brokensep pkgconfig

DESCRIPTION = "WFA certification testing tool for QCA devices"

LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/${LICENSE};md5=550794465ba0ec5312d6919e203a55f9"

PV = "1.0"

DEPENDS = "libnl"

PACKAGE_ARCH ?= "${SOC_ARCH}"

SRCPROJECT = "git://git.codelinaro.org/clo/le//platform/vendor/qcom-opensource/sigma-dut.git;protocol=https"
SRCBRANCH  = "wlan-os-service.qclinux.1.1.r1-rel"
SRCREV     = "e0e36f765531112d502db57f233ad2059c3d3495"

SRC_URI = "${SRCPROJECT};branch=${SRCBRANCH};destsuffix=wlan/utils/sigma-dut \
           file://Makefile.patch"

S = "${WORKDIR}/wlan/utils/sigma-dut"

CFLAGS += "-DLINUX_EMBEDDED"
CFLAGS += "-I ${STAGING_INCDIR}/libnl3"

do_patch() {
    cd ${S}
    patch -p1 < ${WORKDIR}/Makefile.patch
}

do_install() {
    make install DESTDIR=${D} BINDIR=${sbindir}/
}
