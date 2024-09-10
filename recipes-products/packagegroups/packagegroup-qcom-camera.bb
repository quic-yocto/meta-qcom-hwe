SUMMARY = "QCOM Camera Package Group"

LICENSE = "Qualcomm-Technologies-Inc.-Proprietary"

PACKAGE_ARCH = "${SOC_ARCH}"

inherit packagegroup

PROVIDES = "${PACKAGES}"

PACKAGES = "${PN}"

#qcm6490 is a common SOC_FAMILY name for all Kodiak board
RDEPENDS:${PN}:qcom-custom-bsp:qcm6490= "camx-kt camxlib-kt chicdk-kt"

RDEPENDS:${PN}:append:qcm6490:qcom-custom-bsp = " qcom-camera-server"
