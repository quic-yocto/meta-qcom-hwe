inherit pkgconfig autotools-brokensep systemd

DESCRIPTION = "Sensing-hub APIs Library"
LICENSE = "BSD-3-Clause-Clear"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/BSD-3-Clause-Clear;md5=7a434440b651f4a472ca93716d01033a"

DEPENDS = "syslog-plumber"
DEPENDS += "protobuf"
DEPENDS += "protobuf-native"

SRCPROJECT = "git://git.codelinaro.org/clo/le/platform/vendor/qcom-opensource/sensing-hub.git;protocol=https"
SRCBRANCH  = "sensors.lnx.1.0.r1-rel"
SRCREV     = "cf857f7d02199817c77871784849073ebf2babce"

SRC_URI  = "${SRCPROJECT};branch=${SRCBRANCH};destsuffix=sensors/sensing-hub"

S = "${WORKDIR}/sensors/sensing-hub"

EXTRA_OECONF += " --with-systemd"

#Disable the split of debug information into -dbg files
INHIBIT_PACKAGE_DEBUG_SPLIT = "1"

do_compile:prepend() {
    export LD_LIBRARY_PATH="${STAGING_DIR_NATIVE}/usr/lib/x86_64-linux-gnu:${LD_LIBRARY_PATH}"
}

#Skips check for .so symlinks
INSANE_SKIP:${PN} = "dev-so"

# need to export these variables for python-config to work
FILES:${PN} = "${includedir}/*"
FILES:${PN} += "/usr/lib/*"
FILES:${PN} += "/usr/bin/*"
FILES:${PN}-dev  = "${libdir}/*.la ${includedir}"
FILES:${PN} += "${systemd_unitdir}/system/"
FILES:${PN} += "/etc/sensors/*"

PACKAGE_ARCH    ?= "${MACHINE_ARCH}"
