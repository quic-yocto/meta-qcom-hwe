SUMMARY = "Machine specific partition configurations"

LICENSE = "BSD-3-Clause-Clear"
LIC_FILES_CHKSUM = "file://generic-ufs-partitions.conf;beginline=2;endline=3;md5=7848322428d4e017153188678435d4be"

DEPENDS += "gen-partitions-tool-native"

PROVIDES += "virtual/partconf"

FILESEXTRAPATHS:prepend := "${THISDIR}:"

SRC_URI = " \
    file://generic-ufs-partitions.conf \
    file://qcs9100-partitions.conf \
"

S = "${WORKDIR}"

do_configure[noexec] = "1"

PARTCONF ?= "generic-ufs-partitions.conf"
PARTCONF:qcs9100 = "qcs9100-partitions.conf"

do_compile() {
    # Generate partition.xml using gen_partition utility
    ${STAGING_BINDIR_NATIVE}/gen_partition.py \
        -i ${WORKDIR}/${PARTCONF} \
        -o ${B}/${MACHINE}-partition.xml
}

do_install() {
    install -D -m0644 ${B}/${MACHINE}-partition.xml ${D}${sysconfdir}/${MACHINE}-partition.xml
}

inherit deploy
do_deploy() {
    install -m 0644 ${D}${sysconfdir}/${MACHINE}-partition.xml -D ${DEPLOYDIR}/partition.xml
}
addtask deploy before do_build after do_install

PACKAGE_ARCH = "${MACHINE_ARCH}"
