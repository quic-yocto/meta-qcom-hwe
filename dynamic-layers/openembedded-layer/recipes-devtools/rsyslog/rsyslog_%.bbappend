FILESEXTRAPATHS:prepend:qcom := "${THISDIR}/${BPN}:"

SRC_URI += "file://rsyslog.conf.qcom"

PERF_BUILD = "${@oe.utils.conditional('PERFORMANCE_BUILD', '1', "true", "false", d)}"

do_install:append:qcom() {
    if [ "${PERF_BUILD}" = "true" ]; then
        install -m 644 ${WORKDIR}/rsyslog.conf.qcom ${D}${sysconfdir}/rsyslog.conf
    fi
}
