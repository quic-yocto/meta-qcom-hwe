inherit autotools pkgconfig

DESCRIPTION = "Build Google libchrome"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/files/common-licenses/\
${LICENSE};md5=89aea4e17d99a7cacdbeed46a0096b10"

PR = "r0"
DEPENDS = "libevent libmodpb64 gtest syslog-plumber" 

FILESPATH =+ "${WORKSPACE}/:"
SRC_URI = "git://git.codelinaro.org/clo/la/platform/external/libchrome;protocol=https;nobranch=1;rev=b4b96cdfd447daac679b067c3b969cc5ed22a798;destsuffix=libchrome"
SRC_URI += "file://0001-Add-Support-to-build-libchrome.patch \
           file://0001-libchrome-Update-log.h-for-syslog.patch "

S = "${WORKDIR}/libchrome"
CPPFLAGS:append = " ${@bb.utils.contains('VARIANT', 'debug', '-g', '', d)}"
#PARALLEL_MAKE = ""

do_install:append() {
  install -D ${WORKDIR}/build/libchrome.pc ${D}${libdir}/pkgconfig/libchrome.pc
}
#do_compile[noexec]="1"
#do_install[noexec]="1"
