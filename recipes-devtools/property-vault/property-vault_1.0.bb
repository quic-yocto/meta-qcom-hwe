inherit autotools pkgconfig systemd

DESCRIPTION = "property vault managment"
SUMMARY = "property vault managment"
LICENSE = "BSD-3-Clause-Clear"
LIC_FILES_CHKSUM += "file://ll.c;beginline=33;endline=34;md5=aabb851d69c3a788a613fd53fa9db243 \
                     file://ll.h;beginline=33;endline=34;md5=aabb851d69c3a788a613fd53fa9db243 \
                     file://property_vault.c;beginline=33;endline=34;md5=aabb851d69c3a788a613fd53fa9db243 \
                     file://property_vault.h;beginline=33;endline=34;md5=aabb851d69c3a788a613fd53fa9db243"


PR = "r0"



SRC_URI = "git://git.codelinaro.org/clo/le/le-utils;protocol=https;branch=le-utils.qclinux.1.0.r1-rel;rev=bf7a1a626db3e17c6973513ba48ab2c134e4d701;subdir=property-vault"

S = "${WORKDIR}/git"

DEPENDS += "libselinux syslog-plumber glib-2.0"

EXTRA_OECONF = "${@bb.utils.contains('DISTRO_FEATURES', 'systemd', '--with-systemd', '',d)}"
SYSTEMD_SERVICE:${PN} = "property-vault.service"

do_install:append() {
    install -b -m 0644 /dev/null -D ${D}${sysconfdir}/build.prop
}
