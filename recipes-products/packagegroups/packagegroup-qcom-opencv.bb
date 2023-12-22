SUMMARY = "OPENCV package group"

PACKAGE_ARCH = "${MACHINE_ARCH}"
inherit packagegroup
PACKAGES = "\
    packagegroup-qcom-opencv \
    "
RDEPENDS:packagegroup-qcom-opencv += " \
    opencv \
    "

