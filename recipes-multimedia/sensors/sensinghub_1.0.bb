inherit pkgconfig autotools-brokensep systemd

DESCRIPTION = "Sensing-hub APIs Library"
LICENSE = "BSD-3-Clause-Clear"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/BSD-3-Clause-Clear;md5=7a434440b651f4a472ca93716d01033a"

DEPENDS = "syslog-plumber"
DEPENDS += "protobuf"
DEPENDS += "protobuf-native"

SRC_URI  = "git://git.codelinaro.org/clo/le/platform/vendor/qcom-opensource/sensing-hub.git;protocol=https;rev=2bc0ce9a5da92187e5e181e7225f6bb1d6a3ccec;branch=sensors.lnx.1.0.r1-rel;destsuffix=sensors/sensing-hub"
S = "${WORKDIR}/sensors/sensing-hub"

EXTRA_OECONF += " --with-systemd"

#Disable the split of debug information into -dbg files
INHIBIT_PACKAGE_DEBUG_SPLIT = "1"

#Skips check for .so symlinks
INSANE_SKIP:${PN} = "dev-so"

# need to export these variables for python-config to work
FILES:${PN} = "${includedir}/*"
FILES:${PN} += "/usr/lib/*"
FILES:${PN} += "/usr/bin/*"
FILES:${PN}-dev  = "${libdir}/*.la ${includedir}"
FILES:${PN} += "${systemd_unitdir}/system/"

PACKAGE_ARCH    ?= "${MACHINE_ARCH}"
