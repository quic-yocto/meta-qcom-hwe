inherit autotools pkgconfig

DESCRIPTION = "Build BT HOST IPC"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/files/common-licenses/\
${LICENSE};md5=89aea4e17d99a7cacdbeed46a0096b10"

PR = "r0"

DEPENDS = "glib-2.0 fluoride"

SRC_URI += "git://git.codelinaro.org/clo/le/platform/vendor/qcom-opensource/bluetooth.git;protocol=https;rev=87f67a56b05ca12dac03b2d7c833d5e377139936;branch=bt-performant.qclinux.1.0.r1-rel"

BT_SOURCE = "${WORKDIR}"
S = "${WORKDIR}/git"
S_EXT = "${BT_SOURCE}/stack/bluetooth_ext/system_bt_ext"

EXTRA_OEMAKE += 'BT_SOURCE=${BT_SOURCE}'

#CPPFLAGS:append = " -DUSE_ANDROID_LOGGING -DUSE_LIBHW_AOSP"
#CPPFLAGS:append += " ${@bb.utils.contains('VARIANT', 'debug', '-g', '', d)}"
#LDFLAGS:append = " -llog "

EXTRA_OECONF = "--with-glib"
SOLIBS = ".so"

PACKAGE_ARCH = "${MACHINE_ARCH}"
FILES_SOLIBSDEV = ""
