SUMMARY = "QCOM Camera Package Group"

LICENSE = "Qualcomm-Technologies-Inc.-Proprietary"

PACKAGE_ARCH = "${MACHINE_ARCH}"

inherit packagegroup

PROVIDES = "${PACKAGES}"

PACKAGES = "${PN}"

RDEPENDS:${PN} = ' \
    camx-kt \
    camxlib-kt \
    chicdk-kt \
'

