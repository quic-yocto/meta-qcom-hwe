SUMMARY = "gbm"
DESCRIPTION = "Provide QC contributed GBM (Generic Buffer Management) library."

LICENSE = "BSD-3-Clause & BSD-3-Clause-Clear"
BSD-3-Clause_LICENSE  = "file://inc/gbm.h;beginline=2;endline=27"
BSD-3-Clause-Clear_LICENSE = "file://inc/gbm.h;beginline=60;endline=90"

LIC_FILES_CHKSUM = " \
${BSD-3-Clause-Clear_LICENSE};md5=01550572dbf950bfb945602fe114d132 \
${BSD-3-Clause_LICENSE};md5=966a1bac2e99d152d17ed2b6d7ad8bab"

DEPENDS = "glib-2.0 wayland displaydlkm"
PROVIDES += "virtual/libgbm libgbm"

SRC_URI += "git://git.codelinaro.org/clo/le/display/libgbm.git;protocol=https;rev=7184f18e07cf34b9eaab5844849901d928463b89;branch=display.qclinux.1.0.r1-rel \
           file://pkgconfig/gbm.pc \
           "
S = "${WORKDIR}/git"

SRC_URI:append = " file://pkgconfig/gbm.pc"

inherit autotools-brokensep pkgconfig

PACKAGE_ARCH = "${MACHINE_ARCH}"

PREBUILT = "1"

EXTRA_OECONF += "--with-sanitized-headers=${STAGING_INCDIR}/linux-msm/usr/include"
EXTRA_OECONF += " \
    --enable-compilewithdrm \
"

CFLAGS += "-I${STAGING_INCDIR}/glib-2.0/ -I${STAGING_LIBDIR}/glib-2.0/include"
CFLAGS += "-DUSE_GLIB"
LDFLAGS += "-lglib-2.0"


PACKAGE_ARCH ?= "${MACHINE_ARCH}"

# The headers for GBM are contained in a completely separate package. Force
# that subsidiary package to be installed anytime that gbm-dev is.
RPROVIDES:${PN} += "libgbm"

do_install:append(){
    install -d ${D}${libdir}/
    ln -sf libgbm.so  ${D}${libdir}/libgbm.so.1
}

SOLIBS = ".so"
FILES_SOLIBSDEV = ""

PACKAGES = "${PN}-dbg ${PN}"
FILES:${PN}-dbg  = "${libdir}/.debug/* ${bindir}/.debug/* /usr/lib/.debug/*"
FILES:${PN}      = "${libdir}/* /usr/lib/* ${bindir}/* ${includedir}/*"
