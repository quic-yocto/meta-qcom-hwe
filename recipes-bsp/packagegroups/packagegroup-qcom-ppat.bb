inherit packagegroup

SUMMARY = "Group to bring core powersw utility packages"

LICENSE = "Qualcomm-Technologies-Inc.-Proprietary"

THERMAL ?= 'True'
THERMAL:qcs8300 = 'False'

PACKAGES = ' \
        packagegroup-qcom-ppat \
'

PROVIDES = "${PACKAGES}"

RDEPENDS:${PN} += " \
    ${@oe.utils.conditional('THERMAL', 'True', 'thermal-engine', '', d)} \
"
