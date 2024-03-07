inherit autotools pkgconfig systemd useradd

DESCRIPTION = "property vault managment"
SUMMARY = "property vault managment"
LICENSE = "BSD-3-Clause-Clear"
LIC_FILES_CHKSUM = "file://${QCOM_COMMON_LICENSE_DIR}${LICENSE};md5=3771d4920bd6cdb8cbdf1e8344489ee0"

SRC_URI += "git://git.codelinaro.org/clo/le/le-utils.git;protocol=https;rev=989cee526729c3a622a3c2726f45e86636636fa1;branch=le-utils.qclinux.1.0.r2-rel"

S = "${WORKDIR}/git/property-vault"

DEPENDS += "libselinux syslog-plumber glib-2.0"

EXTRA_OECONF = "${@bb.utils.contains('DISTRO_FEATURES', 'systemd', '--with-systemd', '',d)}"

SYSTEMD_SERVICE:${PN} = "property-vault.service persist-property-vault.service"

do_install:append() {
    install -b -m 0644 /dev/null -D ${D}${sysconfdir}/build.prop
    chown system:system ${D}${sysconfdir}/build.prop
    install -b -m 0644 /dev/null -D ${D}/var/leutils/build.prop
    chown system:system ${D}/var/leutils/build.prop
    install -d ${D}${base_sbindir}
    install -m 0755 ${S}/set-persist-prop.sh ${D}${base_sbindir}
    install -d ${D}${systemd_system_unitdir}
    install -m 0644 ${S}/property-vault.service ${D}${systemd_system_unitdir}
    install -m 0644 ${S}/persist-property-vault.service ${D}${systemd_system_unitdir}
}

USERADD_PACKAGES = "${PN}"
USERADD_PARAM:${PN} = "-M system"

FILES:${PN} += " /etc/build.prop /var/leutils/build.prop /sbin/set-persist-prop.sh"
