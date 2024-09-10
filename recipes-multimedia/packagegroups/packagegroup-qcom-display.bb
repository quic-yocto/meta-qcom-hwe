SUMMARY = "QCOM Display package groups"

PACKAGE_ARCH = "${SOC_ARCH}"

inherit packagegroup

LICENSE = "BSD-3-Clause & BSD-3-Clause-Clear"
LICENSE += "& Qualcomm-Technologies-Inc.-Proprietary"

PROVIDES = "${PACKAGES}"

PACKAGES = "${PN}"

RDEPENDS:${PN} = " \
    kernel-module-displaydlkm \
    displaydevicetree \
    libcec \
    "

RDEPENDS:${PN}:append:qcm6490 += " \
display-hal-linux \
"
RDEPENDS:${PN}:append = " \
    libdrm \
    libdrm-tests \
    gbm \
    wayland \
    wayland-protocols \
    weston \
    "


RDEPENDS:${PN}:append:qcm6490 = " \
    display-extn-linux \
    display-color-linux \
    "

RDEPENDS:${PN}:append = " \
    kernel-module-touchdlkm \
    "
