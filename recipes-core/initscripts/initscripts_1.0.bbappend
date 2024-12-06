inherit systemd externalsrc

FILESEXTRAPATHS:prepend := "${THISDIR}/files:"
SRC_URI:append:qcom = " \
    file://automountsdcard.rules \
    file://automountsdcard.sh \
    file://sdcard-mount.service \
    file://coresight_reset_source_sink.sh \
    file://debug-config.service \
    file://debug_config.sh \
    file://debug_config_qcm6490.sh \
    file://debug_config_qcs9100.sh \
    file://log-restrict.service \
    file://logging-restrictions.sh \
    file://modem-start-stop.service \
    file://post_boot.sh \
    file://post-boot.service \
    file://start_stop_modem.sh \
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
    install -m 0755 ${WORKDIR}/coresight_reset_source_sink.sh ${D}${sysconfdir}/initscripts/coresight_reset_source_sink.sh
    ln -sf ${systemd_unitdir}/system/debug-config.service ${D}${systemd_unitdir}/system/multi-user.target.wants/debug-config.service

    # automount sdcard
    install -d ${D}${libdir}/udev/rules.d
    install -m 0644 ${WORKDIR}/automountsdcard.rules ${D}${libdir}/udev/rules.d/automountsdcard.rules
    install -d ${D}${bindir}
    install -m 0755 ${WORKDIR}/automountsdcard.sh ${D}${bindir}/automountsdcard.sh
    install -m 0755 ${WORKDIR}/sdcard-mount.service ${D}${systemd_unitdir}/system/sdcard-mount.service
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
FILES:${PN}-debug-config += "${systemd_unitdir}/system/debug-config.service ${systemd_unitdir}/system/multi-user.target.wants/debug-config.service ${sysconfdir}/initscripts/debug_config_qcm6490.sh ${sysconfdir}/initscripts/debug_config_qcs9100.sh ${sysconfdir}/initscripts/debug_config.sh ${sysconfdir}/initscripts/coresight_reset_source_sink.sh"

PACKAGES =+ "${PN}-automount-sdcard"
FILES:${PN}-automount-sdcard =+ "${libdir}/udev/rules.d/automountsdcard.rules ${bindir}/automountsdcard.sh ${systemd_unitdir}/system/sdcard-mount.service"
