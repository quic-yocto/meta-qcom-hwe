SUMMARY = "ptool compliant partition generation utility"
DESCRIPTION = "Generates partition.xml in ptool suitable format"
SECTION = "devel"

LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = " \
   file://${COREBASE}/meta/files/common-licenses/BSD-3-Clause;md5=550794465ba0ec5312d6919e203a55f9 \
"

RDEPENDS:${PN} += "python3"

FILESEXTRAPATHS:prepend := "${THISDIR}:"

S = "${WORKDIR}"

SRC_URI = " \
    file://gen_partition.py \
"

do_configure[noexec] = "1"
do_compile[noexec] = "1"

do_install () {
   install -m 755 -D ${S}/gen_partition.py ${D}${bindir}/gen_partition.py
}

BBCLASSEXTEND = "native nativesdk"
