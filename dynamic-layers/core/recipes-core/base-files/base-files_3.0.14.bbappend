# Create base-files directories under /var, /home, mnt and /media at run-time to
# skip warnings that are reported at build time while using OSTree for SOTA updates.

FILESEXTRAPATHS:prepend := "${THISDIR}/${BPN}:"

SRC_URI:append:qcom = " file://base-files.conf "

do_install:append:qcom(){
    if ${@bb.utils.contains('DISTRO_FEATURES', 'systemd', 'true', 'false', d)}; then
        install -d ${D}/etc/tmpfiles.d
        install -m 0644 ${WORKDIR}/base-files.conf ${D}/etc/tmpfiles.d/base-files.conf

        rm -rf ${D}/${localstatedir} ${D}/home ${D}/mnt ${D}/media

        # Create /var/volatile at build-time to skip error with empty_var_volatile task
        mkdir -p ${D}${localstatedir}/volatile/
    fi
}

BBCLASSEXTEND = "native nativesdk"
