require pulseaudio.inc

SRC_URI += "git://git.codelinaro.org/clo/le/pulseaudio.git;protocol=https;rev=702778ac4e7b9aa2461859eb2b851ddb278c9e47;branch=pulseaudio.lnx.3.0.r5-rel \
           file://0001-client-conf-Add-allow-autospawn-for-root.patch \
           file://0002-do-not-display-CLFAGS-to-improve-reproducibility-bui.patch \
           file://0001-meson-Check-for-__get_cpuid.patch \
           file://volatiles.04_pulse \
           file://0001-doxygen-meson.build-remove-dependency-on-doxygen-bin.patch \
           file://pulseaudio.service \
           file://system-qcm6490.pa \
           file://daemon_conf_in.patch \
           file://0001-Support-native-sample-rate.patch \
           "

S = "${WORKDIR}/git"

do_compile:prepend() {
	mkdir -p ${S}/libltdl
	cp ${STAGING_LIBDIR}/libltdl* ${S}/libltdl
}

do_install:append() {
	install -d ${D}${systemd_system_unitdir}
	install -m 0644 ${WORKDIR}/pulseaudio.service ${D}${systemd_system_unitdir}
	install -d ${D}${systemd_system_unitdir}/multi-user.target.wants/
	# enable the service for multi-user.target
	ln -sf ${systemd_system_unitdir}/pulseaudio.service \
	       ${D}${systemd_system_unitdir}/multi-user.target.wants/pulseaudio.service
	install -m 0644 ${WORKDIR}/system-${MACHINE}.pa ${D}${sysconfdir}/pulse/system.pa

	for i in $(find ${S}/src/pulsecore/ -type d -printf "pulsecore/%P\n"); do
		[ -n "$(ls ${S}/src/${i}/*.h 2>/dev/null)" ] || continue
		install -d ${D}${includedir}/${i}
		install -m 0644 ${S}/src/${i}/*.h ${D}${includedir}/${i}/
	done
	# not really the expected path for config.h but we can't just
	# put it ${includedir}, it's too generic a name.
	install -m 0644 ${WORKDIR}/build/config.h ${D}${includedir}/pulsecore
}

GROUPADD_PARAM:pulseaudio-server = "-g 5020 pulse"
USERADD_PARAM:pulseaudio-server = "--system --home /var/run/pulse \
                              --no-create-home --shell /bin/false \
                              --groups audio,pulse,input,plugdev --gid pulse pulse"

SYSTEMD_PACKAGES = "${PN}-server"

PACKAGES =+ "libpulsecore-dev"
FILES_libpulsecore-dev = "${includedir}/pulsecore/*"

# Explicitly create this directory for the volatile bind mount to work
FILES:${PN}-server += "/var/lib/pulse"

EXTRA_OEMESON:append = " -Dpa_version=15.0"
EXTRA_OEMESON:append:qcm6490 = " -Dpal-support-card-status=false"
EXTRA_OEMESON:append:qcm6490 = " -Dpal-support-cutils=false"

# Build the pal module on qcm6490
DEPENDS:append:qcm6490 = " pal"
EXTRA_OEMESON:append:qcm6490 = " -Dwith-qal=${STAGING_INCDIR}/pal"
RDEPENDS:pulseaudio-server:append:qcm6490 = " pulseaudio-module-qal-card"
RDEPENDS:pulseaudio-server:append:qcm6490 = " pulseaudio-module-dbus-protocol"

# Build the pal voiceui card on qcm6490
EXTRA_OEMESON:append:qcm6490 = " -Dwith-qal-voiceui=${STAGING_INCDIR}/pal"
RDEPENDS:pulseaudio-server:append:qcm6490 = " pulseaudio-module-qal-voiceui-card"

FILES:${PN}-module-qal-card += "${datadir}/pulseaudio/qal"
FILES:${PN} = "${datadir}/* ${libdir}/* ${sysconfdir}/* ${bindir}/* ${base_libdir}/* ${prefix}/libexec/"
