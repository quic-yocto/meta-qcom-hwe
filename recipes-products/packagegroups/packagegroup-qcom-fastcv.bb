SUMMARY = "QCOM fastCV Proprietary Package Group"

LICENSE = "Qualcomm-Technologies-Inc.-Proprietary"
PACKAGE_ARCH = "${SOC_ARCH}"

inherit packagegroup

PACKAGES = "${PN}"

RDEPENDS:${PN}:qcom-custom-bsp = " \
    qcom-fastcv-binaries \
    "
