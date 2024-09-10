SUMMARY = "Startup script and systemd unit file for the Weston Wayland compositor"

LICENSE = "BSD-3-Clause & BSD-3-Clause-Clear"
LIC_FILES_CHKSUM = " \
    file://${COMMON_LICENSE_DIR}/BSD-3-Clause;md5=550794465ba0ec5312d6919e203a55f9 \
    file://${COMMON_LICENSE_DIR}/BSD-3-Clause-Clear;md5=7a434440b651f4a472ca93716d01033a \
"

PACKAGE_ARCH = "${SOC_ARCH}"

S = "${WORKDIR}"
SRC_URI = " file://init_qti.service \
            file://init_qti"

DISPLAY_SERVICE_FILENAME = "init_qti.service"
FILES:${PN} += "/data/*"

do_install() {
    if ${@bb.utils.contains('DISTRO_FEATURES', 'systemd', 'true', 'false', d)}; then
        install -m 0755 ${S}/init_qti -D ${D}${sysconfdir}/initscripts/init_qti_display
        install -d ${D}/etc/systemd/system/
        install -m 0755 ${S}/${DISPLAY_SERVICE_FILENAME} -D ${D}${sysconfdir}/systemd/system/init_display.service
        install -d ${D}/etc/systemd/system/multi-user.target.wants
        ln -sf /etc/systemd/system/init_display.service ${D}/etc/systemd/system/multi-user.target.wants/init_display.service
    else
        install -d ${D}/${sysconfdir}/init.d
        install -m755 ${S}/init_qti ${D}/${sysconfdir}/init.d/weston
    fi
}

SYSTEMD_SERVICE:${PN} = "init_display.service"

