LICENSE = "Qualcomm-Technologies-Inc.-Proprietary"

PACKAGE_ARCH = "${MACHINE_ARCH}"

inherit packagegroup

PROVIDES = "${PACKAGES}"

PACKAGES = "${PN}"

# Sensors Image and Debugging utilities
RDEPENDS:${PN} = "\
    sensors-ship-qti \
"
