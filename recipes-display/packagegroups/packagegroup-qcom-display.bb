SUMMARY = "QCOM Display package groups"

PACKAGE_ARCH = "${MACHINE_ARCH}"

inherit packagegroup

LICENSE = "BSD-3-Clause & \
           BSD-3-Clause-Clear & \
           Qualcomm-Technologies-Inc.-Proprietary \
           "

PROVIDES = "${PACKAGES}"

PACKAGES = "${PN}"

RDEPENDS:packagegroup-qcom-display = " \
    display-hal-linux \
    kernel-module-displaydlkm \
    displaydevicetree \
    libdrm \
    libdrm-tests \
    gbm \
    wayland \
    wayland-protocols \
    weston \
    display-noship-linux \
    display-ship-linux \
    "
