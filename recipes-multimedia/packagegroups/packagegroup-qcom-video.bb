SUMMARY = "QCOM Video proprietary package groups"
LICENSE = "Qualcomm-Technologies-Inc.-Proprietary"

PACKAGE_ARCH = "${MACHINE_ARCH}"

inherit packagegroup

PROVIDES = "${PACKAGES}"

PACKAGES = "${PN}"

RDEPENDS:${PN} = " \
    qcom-video-firmware \
    videodlkm \
"
