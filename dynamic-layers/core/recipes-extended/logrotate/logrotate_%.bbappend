FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:"

SRC_URI:append:qcom = " file://logrotate.conf "

do_install:append:qcom() {
    if ${@bb.utils.contains('DISTRO_FEATURES', 'systemd', 'true', 'false', d)}; then
        install -D -m 0644 ${WORKDIR}/logrotate.conf ${D}${nonarch_libdir}/tmpfiles.d/${PN}.conf
        rm -rf ${D}${localstatedir}
    fi
}

FILES:${PN} += "${nonarch_libdir}/tmpfiles.d/${PN}.conf"
