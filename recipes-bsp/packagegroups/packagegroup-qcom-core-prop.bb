SUMMARY = "Group to bring core utility packages"

LICENSE = "Qualcomm-Technologies-Inc.-Proprietary"

inherit packagegroup

PROVIDES = "${PACKAGES}"

PACKAGES = ' \
    packagegroup-qcom-core-prop \
'

RDEPENDS:${PN} = " \
    diag \
    diag-router \
    qmi-framework \
    tftp-server \
    time-services \
"
