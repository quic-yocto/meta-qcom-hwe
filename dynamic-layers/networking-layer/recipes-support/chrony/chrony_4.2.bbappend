# Remove ${localstatedir}/lib/chrony for systems with systemd as
# /etc/tmpfiles.d/chronyd.conf will create /var/lib/chrony at run-time

do_install:append:qcom (){
    if ${@bb.utils.contains('DISTRO_FEATURES', 'systemd', 'true', 'false', d)}; then
        rm -rf ${D}${localstatedir}
    fi
}
