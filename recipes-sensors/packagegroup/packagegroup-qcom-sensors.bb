DESCRIPTION  = "QCOM SENSORS package group"

LICENSE = "Qualcomm-Technologies-Inc.-Proprietary"

PACKAGE_ARCH = "${MACHINE_ARCH}"

inherit packagegroup

PROVIDES = "${PACKAGES}"

PACKAGES = "packagegroup-qcom-sensors"

# Sensors Image and Debugging utilities
RDEPENDS:packagegroup-qcom-sensors = "\
    sensors-ship-qti \
"
