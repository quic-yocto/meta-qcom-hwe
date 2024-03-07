inherit autotools-brokensep pkgconfig

DESCRIPTION = "WFA certification testing tool for QCA devices"

LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/${LICENSE};md5=550794465ba0ec5312d6919e203a55f9"

PV = "1.0"

SRC_URI += "git://git.codelinaro.org/clo/le/platform/vendor/qcom-opensource/sigma-dut.git;protocol=https;rev=ce12148ad394b2f2971715adf2c97df2bdbd09f2;branch=wlan-os-service.qclinux.1.1.r1-rel \
           file://Makefile.patch \
           "
PACKAGE_ARCH ?= "${MACHINE_ARCH}"

DEPENDS = "libnl"

CFLAGS += "-DLINUX_EMBEDDED"
CFLAGS += "-I ${STAGING_INCDIR}/libnl3"

S = "${WORKDIR}/git"

do_patch() {
    cd ${S}
    patch -p1 < ${WORKDIR}/Makefile.patch
}

do_install() {
    make install DESTDIR=${D} BINDIR=${sbindir}/
}
