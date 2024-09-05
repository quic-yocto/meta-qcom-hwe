FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:"
# Try to restore sound configuration from /usr/lib/alsa/asound.state
# if /var/lib/alsa/asound.state is unavailable.
SRC_URI += "\
    file://0001-alsactl-add-fallback-for-restoring-from-asound.state.patch \
    file://tmpfiles.conf \
"
do_install:append() {
        install -D -m 0644 ${WORKDIR}/tmpfiles.conf ${D}${nonarch_libdir}/tmpfiles.d/alsa_utils.conf
        (cd ${D}; vardir=${localstatedir#*/}; rmdir -v --parents ${vardir}/lib/alsa)
}

FILES:alsa-utils-alsactl += "${nonarch_libdir}/tmpfiles.d/alsa_utils.conf"
