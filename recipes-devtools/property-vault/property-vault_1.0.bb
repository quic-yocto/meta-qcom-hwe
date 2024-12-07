inherit autotools pkgconfig systemd

DESCRIPTION = "property vault managment"
SUMMARY = "property vault managment"
LICENSE = "BSD-3-Clause-Clear"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/${LICENSE};md5=7a434440b651f4a472ca93716d01033a"

COMPATIBLE_MACHINE = "(qcom)"

SRCPROJECT = "git://git.codelinaro.org/clo/le/le-utils.git;protocol=https"
SRCBRANCH  = "le-utils.qclinux.1.0.r2-rel"
SRCREV     = "80064c0374bc1f7a3238e45ea8da5cb9addae58c"

SRC_URI = "${SRCPROJECT};branch=${SRCBRANCH};destsuffix=le-utils"

S = "${WORKDIR}/le-utils/property-vault"

DEPENDS += "libselinux syslog-plumber glib-2.0 useradd-qcom"
RDEPENDS:${PN} += "useradd-qcom"

PACKAGECONFIG ??= "\
    ${@bb.utils.filter('DISTRO_FEATURES', 'systemd', d)} \
"
PACKAGECONFIG[systemd] = "--with-systemdunitdir=${systemd_system_unitdir}/,--with-systemdunitdir="

SYSTEMD_SERVICE:${PN} = "property-vault.service persist-property-vault.service"

do_install:append() {
    install -b -m 0644 /dev/null -D ${D}${sysconfdir}/build.prop 
    chown system:system ${D}${sysconfdir}/build.prop

}

FILES:${PN} += " /etc/build.prop "

python () {
    mach_overrides = d.getVar('MACHINEOVERRIDES').split(":")
    if ('qcom-base-bsp' in mach_overrides):
        raise bb.parse.SkipRecipe("property-vault not compatible with qcom-base-bsp")
}
