SUMMARY = "QCOM fastCV Proprietary Package Group"

LICENSE = "Qualcomm-Technologies-Inc.-Proprietary"
PACKAGE_ARCH = "${MACHINE_ARCH}"
inherit packagegroup

PACKAGES = "\
    packagegroup-qcom-fastcv \
    "
RDEPENDS:packagegroup-qcom-fastcv += " \
    fastcv-binaries \
    "

