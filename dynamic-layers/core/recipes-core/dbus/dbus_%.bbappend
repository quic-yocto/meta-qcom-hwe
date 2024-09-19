# Avoid warnings with systemd
EXTRA_OECONF:append:qcom = "--runstatedir=/run"

do_install:append:qcom () {
    if ${@bb.utils.contains('DISTRO_FEATURES', 'systemd', 'true', 'false', d)}; then
        rm -rf ${D}${localstatedir}
    fi
}
