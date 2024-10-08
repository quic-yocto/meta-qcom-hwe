SUMMARY = "ptool compliant partition generation utility"
DESCRIPTION = "Generates partition.xml in ptool suitable format"
SECTION = "devel"
LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/${LICENSE};md5=550794465ba0ec5312d6919e203a55f9"

RDEPENDS:${PN} += "python3-xml"

SRC_URI = "file://gen_partition.py"

S = "${WORKDIR}/sources"
UNPACKDIR = "${S}"

INHIBIT_DEFAULT_DEPS = "1"

do_configure[noexec] = "1"
do_compile[noexec] = "1"

do_install() {
    install -m 755 -D ${S}/gen_partition.py ${D}${bindir}/gen_partition.py
}

BBCLASSEXTEND = "native nativesdk"
