inherit autotools-brokensep pkgconfig update-rc.d

DESCRIPTION = "CNSS"

LICENSE          = "Qualcomm-Technologies-Inc.-Proprietary"
LIC_FILES_CHKSUM = "file://${QCOM_COMMON_LICENSE_DIR}${LICENSE};md5=58d50a3d36f27f1a1e6089308a49b403"

PV = "1.0"

SRC_URI = ""

DEPENDS += "qmi-framework libcutils libnl glib-2.0 cld80211-lib"

PACKAGE_ARCH = "${SOC_ARCH}"

CFLAGS += "-I ${WORKSPACE}/qmi-framework/inc"
EXTRA_OECONF = "--enable-debug"
EXTRA_OECONF += "--with-glib"
EXTRA_OECONF += "CLD80211_LIB=${STAGING_INCDIR}/cld80211-lib"
EXTRA_OECONF += "debug_logcat=true interop_issues_detection=true netlink_common=true subnet_detection=true"
EXTRA_OECONF += "cnss_dp_support=true cnss_user_support=true"

INITSCRIPT_NAME = "start_cnss_daemon"
INITSCRIPT_PARAMS = "start 90 2 3 4 5 . stop 10 0 1 6 ."

do_install:append () {
    if ${@bb.utils.contains('DISTRO_FEATURES', 'systemd', 'true', 'false', d)}; then
    install -m 0755 ${S}/cnss_daemon -D ${D}/usr/sbin/cnss_daemon
    install -d ${D}/etc/systemd/system/
    install -m 0644 ${WORKDIR}/start_cnss_daemon.service -D ${D}/etc/systemd/system/start_cnss_daemon.service
    install -d ${D}/etc/systemd/system/multi-user.target.wants/
    ln -sf /etc/systemd/system/start_cnss_daemon.service \
        ${D}/etc/systemd/system/multi-user.target.wants/start_cnss_daemon.service
    fi
}
