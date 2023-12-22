SUMMARY = "QCOM Camera Package Group"

LICENSE = "Qualcomm-Technologies-Inc.-Proprietary"

PACKAGE_ARCH = "${MACHINE_ARCH}"

inherit packagegroup

PROVIDES = "${PACKAGES}"

PACKAGES = ' \
    packagegroup-qcom-camera \
'

RDEPENDS:packagegroup-qcom-camera = ' \
    camx \
    camxlib \
    chicdk \
'

