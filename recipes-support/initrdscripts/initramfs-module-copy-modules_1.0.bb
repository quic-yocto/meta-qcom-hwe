SUMMARY = "initramfs-framework module for copying kernel modules from initramfs to rootfs"

LICENSE = "BSD-3-Clause-Clear"
LIC_FILES_CHKSUM = "file://copy-modules.sh;beginline=2;endline=3;md5=dbc56fe88f13c32cfaafe479bd187292"

SRC_URI = "file://copy-modules.sh"

S = "${WORKDIR}"

do_install() {
    install -d ${D}/init.d
    install -m 0755 ${WORKDIR}/copy-modules.sh ${D}/init.d/95-copy_modules
}

FILES:${PN} = "/init.d/"
RDEPENDS:${PN} = "initramfs-framework-base ${VIRTUAL-RUNTIME_base-utils}"
