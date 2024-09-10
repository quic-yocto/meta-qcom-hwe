inherit autotools

DESCRIPTION = "audio node rules"
LICENSE = "BSD-3-Clause-Clear"
LIC_FILES_CHKSUM = "file://audio-node.rules;beginline=1;endline=2;md5=0ce4c104f1c8ea3c92d9f1e478703a4d"

SRC_URI += "file://audio-node.rules"

do_compile[noexec] = "1"

S = "${WORKDIR}"

do_install() {
        install -m 0644 ${S}/audio-node.rules -D ${D}${nonarch_base_libdir}/udev/rules.d/audio-node.rules

}

FILES_${PN} += "${systemd_unitdir}/system/* ${nonarch_base_libdir}/udev/*"
