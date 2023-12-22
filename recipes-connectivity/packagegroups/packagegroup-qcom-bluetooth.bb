SUMMARY = "Package group to bring in BT releated packages for LE system"

LICENSE = "BSD-3-Clause"

PACKAGE_ARCH = "${TUNE_PKGARCH}"
inherit packagegroup

PROVIDES = "${PACKAGES}"

PACKAGES = ' \
           packagegroup-qcom-bluetooth \
'

BTVENDOR ?= 'False'
BTVENDOR:qcm6490 = 'True'

RDEPENDS:${PN} = "\
    fluoride \
    btvendorhal \
    libchrome \
    bt-app \
    bt-cert \
    bt-dlkm-kernel \
    btdevicetree "
