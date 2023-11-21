SUMMARY = "Machine specific partition configurations"

LICENSE = "BSD-3-Clause-Clear"
LIC_FILES_CHKSUM = "file://generic-ufs-partitions.conf;beginline=2;endline=3;md5=7848322428d4e017153188678435d4be"

FILESEXTRAPATHS:prepend := "${THISDIR}:"

S = "${WORKDIR}"

SRC_URI = " \
    file://generic-ufs-partitions.conf \
"

do_configure[noexec] = "1"
do_compile[noexec] = "1"

do_install() {
    install -D -m0644 ${WORKDIR}/generic-ufs-partitions.conf ${D}${sysconfdir}/partitions.conf
}

PACKAGE_ARCH = "${MACHINE_ARCH}"

ALLOW_EMPTY:${PN} = "1"

BBCLASSEXTEND = "native nativesdk"
