SUMMARY = "QCOM Display package groups"

PACKAGE_ARCH = "${SOC_ARCH}"

inherit packagegroup

LICENSE = "BSD-3-Clause & BSD-3-Clause-Clear"
LICENSE += "& Qualcomm-Technologies-Inc.-Proprietary"

PROVIDES = "${PACKAGES}"

PACKAGES = "${PN}"

RDEPENDS:${PN} = " \
    libcec \
    libdrm \
    libdrm-tests \
    gbm \
    wayland \
    wayland-protocols \
    weston \
    "

RDEPENDS:${PN}:append:qcm6490 = " \
    kernel-module-displaydlkm \
    qcom-display-hal-linux \
    qcom-displaydevicetree \
    qcom-display-extn-linux \
    qcom-display-color-linux \
    "
RDEPENDS:${PN}:append:qcom-custom-bsp = " \
    kernel-module-qcom-touchdlkm \
    "

