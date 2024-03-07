FILESEXTRAPATHS:prepend := "${THISDIR}/${BPN}:"

SRC_URI:append:qcom = " file://0001-Setting-default-values-in-main.conf.patch"

do_install:append:qcom() {
    install -v -m 0644  ${S}/src/main.conf ${D}${sysconfdir}/bluetooth/
}
