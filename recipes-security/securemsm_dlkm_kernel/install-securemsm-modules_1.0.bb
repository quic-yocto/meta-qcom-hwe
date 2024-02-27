DESCRIPTION = "Script for installing securemsm kernel modules"

LICENSE = "Qualcomm-Technologies-Inc.-Proprietary"
LIC_FILES_CHKSUM = "file://${QCOM_COMMON_LICENSE_DIR}/${LICENSE};md5=58d50a3d36f27f1a1e6089308a49b403"

SRC_URI += "file://smcinvoke.service"
SRC_URI += "file://start_smcinvoke_le"
SRC_URI += "file://tz_log.service"

INITSCRIPT_NAME = "install-securemsm-modules"

inherit update-rc.d systemd pkgconfig

FILES:${PN} += "${systemd_unitdir}/system/"
FILES:${PN} += "${systemd_unitdir}/system/*"
FILES:${PN} += "${sysconfdir}/initscripts/*"

do_install:append() {
    install -d ${D}${sysconfdir}/initscripts
    install -d ${D}${systemd_unitdir}/system/multi-user.target.wants/

	install -m 0755 ${WORKDIR}/start_smcinvoke_le ${D}${sysconfdir}/initscripts

	install -m 0644 ${WORKDIR}/smcinvoke.service -D ${D}${systemd_unitdir}/system/smcinvoke.service
	ln -sf ${systemd_unitdir}/system/smcinvoke.service ${D}${systemd_unitdir}/system/multi-user.target.wants/smcinvoke.service
	install -m 0644 ${WORKDIR}/tz_log.service -D ${D}${systemd_unitdir}/system/tz_log.service

	ln -sf ${systemd_unitdir}/system/tz_log.service ${D}${systemd_unitdir}/system/multi-user.target.wants/tz_log.service
}

SYSTEMD_SERVICE:${PN} = "smcinvoke.service tz_log.service"
