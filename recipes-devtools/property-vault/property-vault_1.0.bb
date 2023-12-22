inherit autotools pkgconfig systemd useradd

DESCRIPTION = "property vault managment"
SUMMARY = "property vault managment"
LICENSE = "BSD-3-Clause-Clear"
LIC_FILES_CHKSUM += "file://ll.c;beginline=33;endline=34;md5=aabb851d69c3a788a613fd53fa9db243 \
                     file://ll.h;beginline=33;endline=34;md5=aabb851d69c3a788a613fd53fa9db243 \
                     file://property_vault.c;beginline=33;endline=34;md5=aabb851d69c3a788a613fd53fa9db243 \
                     file://property_vault.h;beginline=33;endline=34;md5=aabb851d69c3a788a613fd53fa9db243"

SRC_URI += "git://git.codelinaro.org/clo/le/le-utils.git;protocol=https;rev=26df736e1e803e5552349ee7aae0048503d4df97;branch=le-utils.qclinux.1.0.r2-rel"

S = "${WORKDIR}/git/property-vault"

DEPENDS += "libselinux syslog-plumber glib-2.0"

EXTRA_OECONF = "${@bb.utils.contains('DISTRO_FEATURES', 'systemd', '--with-systemd', '',d)}"
SYSTEMD_SERVICE:${PN} = "property-vault.service"

do_install:append() {
    install -b -m 0644 /dev/null -D ${D}${sysconfdir}/build.prop
    chown system:system ${D}${sysconfdir}/build.prop
}

USERADD_PACKAGES = "${PN}"
USERADD_PARAM:${PN} = "-M system"
