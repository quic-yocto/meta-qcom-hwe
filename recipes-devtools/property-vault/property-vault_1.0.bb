inherit autotools pkgconfig systemd useradd

DESCRIPTION = "property vault managment"
SUMMARY = "property vault managment"
LICENSE = "BSD-3-Clause-Clear"
LIC_FILES_CHKSUM = "file://${QCOM_COMMON_LICENSE_DIR}${LICENSE};md5=3771d4920bd6cdb8cbdf1e8344489ee0"

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
