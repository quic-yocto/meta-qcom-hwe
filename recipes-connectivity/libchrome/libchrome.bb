inherit autotools pkgconfig

DESCRIPTION = "Build Google libchrome"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/${LICENSE};md5=89aea4e17d99a7cacdbeed46a0096b10"

DEPENDS = "libevent libmodpb64 gtest syslog-plumber" 

SRC_URI = "git://git.codelinaro.org/clo/la/platform/external/libchrome;protocol=https;nobranch=1;rev=b4b96cdfd447daac679b067c3b969cc5ed22a798;destsuffix=libchrome"
SRC_URI += "file://0001-Add-Support-to-build-libchrome.patch \
           file://0001-libchrome-Update-log.h-for-syslog.patch "

S = "${WORKDIR}/libchrome"

do_install:append() {
  install -D ${WORKDIR}/build/libchrome.pc ${D}${libdir}/pkgconfig/libchrome.pc
}
