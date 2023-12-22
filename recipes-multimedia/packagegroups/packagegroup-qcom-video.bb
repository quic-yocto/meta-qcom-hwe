SUMMARY = "QCOM Video proprietary package groups"
LICENSE = "Qualcomm-Technologies-Inc.-Proprietary"

PACKAGE_ARCH = "${MACHINE_ARCH}"

inherit packagegroup

PROVIDES = "${PACKAGES}"

PACKAGES = "packagegroup-qcom-video"

RDEPENDS:packagegroup-qcom-video = " \
    qcom-video-firmware \
"
