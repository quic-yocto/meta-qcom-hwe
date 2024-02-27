SUMMARY = "OPENCV package group"

PACKAGE_ARCH = "${MACHINE_ARCH}"
inherit packagegroup

PACKAGES = "${PN}"

RDEPENDS:${PN}:append = " \
    opencv \
    "

