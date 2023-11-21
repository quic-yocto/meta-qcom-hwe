SUMMARY = "A System and service manager"
HOMEPAGE = "http://www.freedesktop.org/wiki/Software/systemd"

DESCRIPTION = "systemd is a system and service manager for Linux, compatible with \
SysV and LSB init scripts. systemd provides aggressive parallelization \
capabilities, uses socket and D-Bus activation for starting services, \
offers on-demand starting of daemons, keeps track of processes using \
Linux cgroups, supports snapshotting and restoring of the system \
state, maintains mount and automount points and implements an \
elaborate transactional dependency-based service control logic. It can \
work as a drop-in replacement for sysvinit."

LICENSE = "GPL-2.0-only & LGPL-2.1-only"
LIC_FILES_CHKSUM = "file://LICENSE.GPL2;md5=751419260aa954499f7abaabaa882bbe \
                    file://LICENSE.LGPL2.1;md5=4fbd65380cdd255951079008b364516c"

SRCREV = "2e7504449a51fb38db9cd2da391c6434f82def51"
SRCBRANCH = "v254-stable"
SRC_URI = "git://github.com/systemd/systemd-stable.git;protocol=https;branch=${SRCBRANCH}"

S = "${WORKDIR}/git"

require conf/image-uefi.conf

DEPENDS = "intltool-native libcap util-linux gperf-native python3-jinja2-native python3-pyelftools-native"
DEPENDS:class-native = " python3-pefile-native"

inherit meson pkgconfig gettext

LDFLAGS:prepend = "${@ " ".join(d.getVar('LD').split()[1:])} "

EFI_LD = "bfd"
LDFLAGS:append = " -fuse-ld=${EFI_LD}"

do_write_config[vardeps] += "EFI_LD"
do_write_config:append() {
    cat >${WORKDIR}/meson-${PN}.cross <<EOF
[binaries]
c_ld = ${@meson_array('EFI_LD', d)}
EOF
}

MESON_CROSS_FILE:append = " --cross-file ${WORKDIR}/meson-${PN}.cross"

MESON_TARGET = "systemd-boot"

EXTRA_OEMESON += "-Defi=true \
                  -Dbootloader=true \
                  -Dman=false \
                  "

# install to the image as boot*.efi if its the EFI_PROVIDER,
# otherwise install as the full name.
# This allows multiple bootloaders to coexist in a single image.
python __anonymous () {
    import re
    target = d.getVar('TARGET_ARCH')
    prefix = "" if d.getVar('EFI_PROVIDER') == "systemd-boot" else "systemd-"
    systemdimage = prefix + d.getVar("EFI_BOOT_IMAGE")
    d.setVar("SYSTEMD_BOOT_IMAGE", systemdimage)
    prefix = "systemd-" if prefix == "" else ""
    d.setVar("SYSTEMD_BOOT_IMAGE_PREFIX", prefix)
}

FILES:${PN} = "${EFI_FILES_PATH}/${SYSTEMD_BOOT_IMAGE}"

RDEPENDS:${PN} += "virtual-systemd-bootconf"

CFLAGS:append:libc-musl = " -D__DEFINED_wchar_t"

COMPATIBLE_HOST = "(aarch64.*|arm.*|x86_64.*|i.86.*)-linux"
COMPATIBLE_HOST:x86-x32 = "null"

do_install:class-target() {
    install -d ${D}${EFI_FILES_PATH}
    install -d ${DEPLOY_DIR_IMAGE}
    install ${B}/src/boot/efi/systemd-boot*.efi ${D}${EFI_FILES_PATH}/${SYSTEMD_BOOT_IMAGE}
}

do_configure:class-native() {
  mkdir -p ${B}/meson-logs/
  touch ${B}/meson-logs/meson-log.txt
}

do_compile:class-native() {
}

do_install:class-native() {
    install -d ${D}${bindir}
    install -m 0755 ${S}/src/ukify/ukify.py ${D}${bindir}/ukify
}

inherit deploy
do_deploy() {
}
do_deploy:class-target() {
    install ${B}/src/boot/efi/systemd-boot*.efi ${DEPLOYDIR}
    install ${B}/src/boot/efi/linux*.efi.stub ${DEPLOYDIR}
    install ${B}/src/boot/efi/addon*.efi.stub ${DEPLOYDIR}
}
addtask deploy before do_build after do_install

RRECOMMENDS:${PN}:class-native = ""
RDEPENDS:${PN}:class-native = ""

BBCLASSEXTEND += "native"
