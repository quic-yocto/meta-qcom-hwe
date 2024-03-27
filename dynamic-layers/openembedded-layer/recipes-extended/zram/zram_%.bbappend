FILESEXTRAPATHS:prepend := "${THISDIR}:"

SRC_URI += " \
           file://zram/zram-swap-init-update \
"

do_install:append:qcom () {
    if ${@bb.utils.contains('DISTRO_FEATURES', 'systemd', 'true', 'false', d)}; then
        install -m 0755 ${WORKDIR}/zram/zram-swap-init-update ${D}${libexecdir}/zram-swap-init
    fi
}
