SUMMARY = "QCOM Display package groups"

PACKAGE_ARCH = "${MACHINE_ARCH}"

inherit packagegroup

LICENSE = "BSD-3-Clause & BSD-3-Clause-Clear"

PROVIDES = "${PACKAGES}"

PACKAGES = "${PN}"

RDEPENDS:${PN} = " \
    display-hal-linux \
    kernel-module-displaydlkm \
    displaydevicetree \
    libcec \
    "

##### bbappended from meta-qti-display #####
RDEPENDS:${PN}:append = " \
    libdrm \
    libdrm-tests \
    gbm \
    wayland \
    wayland-protocols \
    weston \
    "

##### bbappended from meta-qti-display-prop #####
LICENSE += "& Qualcomm-Technologies-Inc.-Proprietary"

RDEPENDS:${PN}:append = " \
    display-extn-linux \
    display-color-linux \
    "

##### bbappended from meta-qti-touch #####
RDEPENDS:${PN}:append = " \
    kernel-module-touchdlkm \
    "
