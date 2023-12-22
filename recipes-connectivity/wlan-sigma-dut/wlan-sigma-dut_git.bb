inherit autotools-brokensep pkgconfig

DESCRIPTION = "WFA certification testing tool for QCA devices"
HOMEPAGE = "https://github.com/qca/sigma-dut"
LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/${LICENSE};md5=550794465ba0ec5312d6919e203a55f9"

PV = "1.0"
PR = "r0"

FILESPATH =+ "${WORKSPACE}:"
SRC_URI = "file://wlan/utils/sigma-dut/"
PACKAGE_ARCH ?= "${MACHINE_ARCH}"

SRC_DIR = "${WORKSPACE}/wlan/utils/sigma-dut/"
SRC_URI += "file://Makefile.patch"

DEPENDS = "libnl"

CFLAGS += "-DLINUX_EMBEDDED"
CFLAGS += "-I ${STAGING_INCDIR}/libnl3"

S = "${WORKDIR}/wlan/utils/sigma-dut"

do_patch() {
    cd ${S}
    patch -p1 < ${WORKDIR}/Makefile.patch
}

do_install() {
    if [ ${BASEMACHINE} == "sdxlemur" ]; then
        make install DESTDIR=${D} BINDIR=${sbindir}/mcc
        ln -sf /systemrw/wlan/bin/sigma_dut ${D}/usr/sbin/sigma_dut
    else
        make install DESTDIR=${D} BINDIR=${sbindir}/
    fi
}
