LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/files/common-licenses/\
${LICENSE};md5=89aea4e17d99a7cacdbeed46a0096b10"

DESCRIPTION = "Start up script for btproperty"

SRC_URI +="file://start_btproperty"
SRC_URI +="file://btproperty.service"

PR = "r3"

INITSCRIPT_NAME = "start_btproperty"
INITSCRIPT_PARAMS = "start 8 2 3 4 5 . stop 20 0 1 6 ."

inherit update-rc.d systemd pkgconfig

FILES:${PN} += "${systemd_unitdir}/system/"

do_install:append() {
       if ${@bb.utils.contains('DISTRO_FEATURES', 'systemd', 'true', 'false', d)}; then
        install -m 0755 ${WORKDIR}/start_btproperty -D ${D}${sysconfdir}/initscripts/${INITSCRIPT_NAME}
        install -d ${D}/etc/systemd/system/
        install -m 0644 ${WORKDIR}/btproperty.service -D ${D}/etc/systemd/system/btproperty.service
        install -d ${D}/etc/systemd/system/multi-user.target.wants/
        # enable the service for multi-user.target
        ln -sf /etc/systemd/system/btproperty.service \
              ${D}/etc/systemd/system/multi-user.target.wants/btproperty.service
       else
        install -m 0755 ${WORKDIR}/start_btproperty -D ${D}${sysconfdir}/init.d/${INITSCRIPT_NAME}
       fi
}
