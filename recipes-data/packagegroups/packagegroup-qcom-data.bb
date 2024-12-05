LICENSE = "Qualcomm-Technologies-Inc.-Proprietary"
LICENSE += "& BSD-3-Clause"

PACKAGE_ARCH = "${MACHINE_ARCH}"

inherit packagegroup

PROVIDES = "${PACKAGES}"

PACKAGES = "${PN}"

RDEPENDS:${PN} = " "

LICENSE += "& BSD-3-Clause"

RDEPENDS:${PN}:append:qcs8550 = " \
             kernel-module-qps615 \
             qps615-devicetree \
"

RDEPENDS:${PN}:append:qcm6490 = " \
             kernel-module-qps615 \
             qps615-firmware \
"

RDEPENDS:${PN}:append:qcs9100 = " \
             dpdk\
"
