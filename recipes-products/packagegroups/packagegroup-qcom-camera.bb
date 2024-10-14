SUMMARY = "QCOM Camera Package Group"

LICENSE = "Qualcomm-Technologies-Inc.-Proprietary"

PACKAGE_ARCH = "${SOC_ARCH}"

inherit packagegroup

PROVIDES = "${PACKAGES}"

PACKAGES = "${PN}"

#qcm6490 is a common SOC_FAMILY name for all Kodiak board
RDEPENDS:${PN}:qcom-custom-bsp:qcm6490= "camx-kt camxlib-kt chicdk-kt"

#qcs9100 is a common SOC_FAMILY name for all Lemans board
RDEPENDS:${PN}:qcom-custom-bsp:qcs9100 = "camxcommon camxlib camx chicdk cameradlkm"

#qcs8300 is a common SOC_FAMILY name for all Lemans board
RDEPENDS:${PN}:qcom-custom-bsp:qcs8300 = "camxcommon camxlib camx chicdk cameradlkm"

RDEPENDS:${PN}:append:qcm6490:qcom-custom-bsp = " qcom-camera-server"
