inherit packagegroup

SUMMARY = "Qaulcomm Synx Proprietary Package Group"

LICENSE = "Qualcomm-Technologies-Inc.-Proprietary"
PACKAGE_ARCH = "${MACHINE_ARCH}"

PACKAGES = "${PN}"

RDEPENDS:${PN} = " \
    libos \
    libthreadutils \
    libsynx \
    synx-kernel \
    "

