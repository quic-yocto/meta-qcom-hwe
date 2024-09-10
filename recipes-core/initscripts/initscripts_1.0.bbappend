inherit systemd externalsrc

FILESEXTRAPATHS:prepend := "${THISDIR}/files:"
SRC_URI:append:qcom = " \
    file://post_boot.sh \
    file://logging-restrictions.sh \
    file://start_stop_modem.sh \
    file://debug_config.sh \
    file://debug_config_qcm6490.sh \
    file://log-restrict.service \
    file://post-boot.service \
    file://modem-start-stop.service \
    file://debug-config.service \
    file://debug_config_qcs9100.sh \
"

do_install:append:qcom() {
    install -d ${D}${systemd_unitdir}/system/
    install -d ${D}${systemd_unitdir}/system/multi-user.target.wants/
    install -d ${D}${systemd_unitdir}/system/ffbm.target.wants/
    install -d ${D}${sysconfdir}/initscripts/

    # postboot
    install -m 0755 ${WORKDIR}/post_boot.sh ${D}${sysconfdir}/initscripts/post_boot.sh
    install -m 0644 ${WORKDIR}/post-boot.service -D ${D}${systemd_unitdir}/system/post-boot.service
    ln -sf ${systemd_unitdir}/system/post-boot.service ${D}${systemd_unitdir}/system/multi-user.target.wants/post-boot.service

    # log-restrict
    install -m 0755 ${WORKDIR}/logging-restrictions.sh -D ${D}${sysconfdir}/initscripts/log_restrict.sh
    install -m 0644 ${WORKDIR}/log-restrict.service -D ${D}${systemd_unitdir}/system/log-restrict.service
    ln -sf ${systemd_unitdir}/system/log-restrict.service ${D}${systemd_unitdir}/system/multi-user.target.wants/log-restrict.service
    ln -sf ${systemd_unitdir}/system/log-restrict.service ${D}${systemd_unitdir}/system/ffbm.target.wants/log-restrict.service

    # modem start or stop
    install -m 0755 ${WORKDIR}/start_stop_modem.sh -D ${D}${sysconfdir}/initscripts/start_stop_modem.sh
    install -m 0644 ${WORKDIR}/modem-start-stop.service -D ${D}${systemd_unitdir}/system/modem-start-stop.service
    ln -sf ${systemd_unitdir}/system/modem-start-stop.service ${D}${systemd_unitdir}/system/multi-user.target.wants/modem-start-stop.service

    # kernel debug configuration
    install -m 0755 ${WORKDIR}/debug_config.sh ${D}${sysconfdir}/initscripts/debug_config.sh
    install -m 0755 ${WORKDIR}/debug_config_qcm6490.sh ${D}${sysconfdir}/initscripts/debug_config_qcm6490.sh
    install -m 0755 ${WORKDIR}/debug_config_qcs9100.sh ${D}${sysconfdir}/initscripts/debug_config_qcs9100.sh
    install -m 0644 ${WORKDIR}/debug-config.service -D ${D}${systemd_unitdir}/system/debug-config.service
    ln -sf ${systemd_unitdir}/system/debug-config.service ${D}${systemd_unitdir}/system/multi-user.target.wants/debug-config.service
}

S = "${WORKDIR}"

INITSCRIPT_PACKAGES =+ "${PN}-log-restrict"
INITSCRIPT_NAME:${PN}-log-restrict = "log_restrict.sh"
INITSCRIPT_PARAMS:${PN}-log-restrict = "start 99 2 3 4 5 ."
INITSCRIPT_PARAMS:${PN}-log-restrict += "stop 1 0 1 6 ."

PACKAGES =+ "${PN}-log-restrict"
FILES:${PN}-log-restrict += "${systemd_unitdir}/system/log-restrict.service ${systemd_unitdir}/system/multi-user.target.wants/log-restrict.service ${systemd_unitdir}/system/ffbm.target.wants/log-restrict.service ${sysconfdir}/initscripts/log_restrict.sh"

INITSCRIPT_PACKAGES =+ "${PN}-post-boot"
INITSCRIPT_NAME:${PN}-post-boot = "post_boot.sh"

PACKAGES =+ "${PN}-post-boot"
FILES:${PN}-post-boot += "${systemd_unitdir}/system/post-boot.service ${systemd_unitdir}/system/multi-user.target.wants/post-boot.service ${sysconfdir}/initscripts/post_boot.sh"
ALLOW_EMPTY:${PN}-functions = "1"

INITSCRIPT_PACKAGES =+ "${PN}-modem-start-stop"
INITSCRIPT_NAME:${PN}-modem-start-stop = "start_stop_modem.sh"

PACKAGES =+ "${PN}-modem-start-stop"
FILES:${PN}-modem-start-stop += "${systemd_unitdir}/system/modem-start-stop.service ${systemd_unitdir}/system/multi-user.target.wants/modem-start-stop.service ${sysconfdir}/initscripts/start_stop_modem.sh"

INITSCRIPT_PACKAGES =+ "${PN}-debug-config"
INITSCRIPT_NAME:${PN}-debug-config = "debug_config.sh"

PACKAGES =+ "${PN}-debug-config"
FILES:${PN}-debug-config += "${systemd_unitdir}/system/debug-config.service ${systemd_unitdir}/system/multi-user.target.wants/debug-config.service ${sysconfdir}/initscripts/debug_config_qcm6490.sh ${sysconfdir}/initscripts/debug_config_qcs9100.sh ${sysconfdir}/initscripts/debug_config.sh"
