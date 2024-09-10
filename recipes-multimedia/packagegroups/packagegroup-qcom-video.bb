SUMMARY = "QCOM Video proprietary package groups"
LICENSE = "Qualcomm-Technologies-Inc.-Proprietary"

inherit packagegroup

PROVIDES = "${PACKAGES}"

PACKAGES = "${PN}"

RDEPENDS:${PN} = " \
    qcom-video-firmware \
    qcom-videodlkm \
"
