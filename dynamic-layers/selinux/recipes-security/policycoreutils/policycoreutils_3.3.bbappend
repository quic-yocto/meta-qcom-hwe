# Create base-files directories under /var, /home, mnt and /media at run-time to
# skip warnings that are reported at build time while using OSTree for SOTA updates.

FILESEXTRAPATHS:prepend := "${THISDIR}/${BPN}:"

SRC_URI:append:qcom = " file://policycoreutils.conf "

do_install:append:qcom(){
    if ${@bb.utils.contains('DISTRO_FEATURES', 'systemd', 'true', 'false', d)}; then
        install -d ${D}/etc/tmpfiles.d
        install -m 0644 ${WORKDIR}/policycoreutils.conf ${D}/etc/tmpfiles.d/policycoreutils.conf

        rm -rf ${D}/${localstatedir}
    fi
}
