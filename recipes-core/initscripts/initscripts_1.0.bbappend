inherit systemd externalsrc

FILESEXTRAPATHS:prepend := "${THISDIR}/files:"
SRC_URI:append = " \
    file://post_boot.sh \
    file://logging-restrictions.sh \
    file://log-restrict.service \
    file://post-boot.service \
"

do_install:append() {
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
