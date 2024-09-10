SUMMARY = "Package group to bring in BT releated packages for LE system"

LICENSE = "BSD-3-Clause"
LICENSE = "Qualcomm-Technologies-Inc.-Proprietary"

PACKAGE_ARCH = "${TUNE_PKGARCH}"
inherit packagegroup

PROVIDES = "${PACKAGES}"

PACKAGES = "${PN}"

BTVENDOR ?= 'False'

BTVENDOR:qcm6490 = 'True'

RDEPENDS:${PN} = "\
    fluoride \
    btvendorhal \
    libchrome \
    qcom-audioroute \
    qcom-pa-bt-audio \
    bt-app \
    bt-cert \
    bt-dlkm-kernel \
    bthost-ipc \
    btftm \
    btdevicetree \
    bluetooth-tools "

PACKAGE_ARCH = "${MACHINE_ARCH}"

BTVENDORPROP ?= 'False'

RDEPENDS:${PN} += " \
    ${@oe.utils.conditional('BTVENDORPROP', 'True', 'qesl-ap', '', d)} \
    ${@oe.utils.conditional('BTVENDORPROP', 'True', 'qesl-me', '', d)} \
"
