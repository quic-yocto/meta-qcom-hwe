FILESEXTRAPATHS:prepend := "${THISDIR}/${BPN}:"
 
SRC_URI:append:qcom = " file://cni.conf "

do_install:append:qcom() {
    if ${@bb.utils.contains('DISTRO_FEATURES', 'systemd', 'true', 'false', d)}; then
        # Install cni.conf to create /opt/cni at run-time using systemd tmpfiles.d
        install -d ${D}/${sysconfdir}/tmpfiles.d
        install -m 644 ${WORKDIR}/cni.conf ${D}/${sysconfdir}/tmpfiles.d/cni.conf
        # Remove /opt/cni installed at build time as it will be created at run-time with systemd tmpfiles.d
        rm -rf ${D}/opt
    fi
}
