# Create /var/lib/lxc at run-time for systems using systemd

FILESEXTRAPATHS:prepend := "${THISDIR}/${BPN}:"

SRC_URI:append:qcom = " file://0001-PENDING-lxc-Create-var-lib-lxc-at-runtime-for-OSTree-SOTA-compatibility.patch "

do_install:append:qcom(){
    if ${@bb.utils.contains('DISTRO_FEATURES', 'systemd', 'true', 'false', d)}; then
        # /var/lib/lxc is created at run-time using systemd
        # Hence, remove /var that is installed at build-time
        rm -rf ${D}${localstatedir}
    fi
}
