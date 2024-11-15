# Create /var/lib/arpd directory at run-time to skip warnings
# that are reported at build time while using OSTree for SOTA updates.

FILESEXTRAPATHS:prepend := "${THISDIR}/${BPN}:"

SRC_URI:append:qcom = " file://iproute2.conf"

do_install:append:qcom(){
    if ${@bb.utils.contains('DISTRO_FEATURES', 'systemd', 'true', 'false', d)}; then
        install -d ${D}/etc/tmpfiles.d
        install -m 0644 ${WORKDIR}/iproute2.conf ${D}/etc/tmpfiles.d/iproute2.conf
        rm -rf ${D}${localstatedir}
    fi
}
