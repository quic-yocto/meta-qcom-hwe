SUMMARY = "QCOM fastCV Proprietary Package Group"

LICENSE = "Qualcomm-Technologies-Inc.-Proprietary"
PACKAGE_ARCH = "${MACHINE_ARCH}"
inherit packagegroup

PACKAGES = "${PN}"

RDEPENDS:${PN}:append = " \
    fastcv-binaries \
    "
