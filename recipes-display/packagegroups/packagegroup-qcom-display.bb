SUMMARY = "QCOM Display package groups"

PACKAGE_ARCH = "${MACHINE_ARCH}"

inherit packagegroup

LICENSE = "BSD-3-Clause & BSD-3-Clause-Clear & Qualcomm-Technologies-Inc.-Proprietary"

PROVIDES = "${PACKAGES}"

PACKAGES = "${PN}"

RDEPENDS:${PN} = " \
    display-hal-linux \
    kernel-module-displaydlkm \
    displaydevicetree \
    "

RDEPENDS:${PN}:append = " \
    display-extn-linux \
    display-color-linux \
    "

RDEPENDS:${PN}:append = " \
    libdrm \
    libdrm-tests \
    gbm \
    wayland \
    wayland-protocols \
    weston \
    "
