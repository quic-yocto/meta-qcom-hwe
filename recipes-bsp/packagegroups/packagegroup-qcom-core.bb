SUMMARY = "Group to bring Core Open Source Packages"
LICENSE = "BSD-3-Clause"
LICENSE += "& Qualcomm-Technologies-Inc.-Proprietary"

inherit packagegroup

PROVIDES = "${PACKAGES}"

SECCONFIG ?= 'True'
USB ?= 'True'

PACKAGES = ' \
    ${PN} \
    ${PN}-recovery \
    ${PN}-vm \
'

RDEPENDS:${PN} = " \
    rpmsgexport \
    ${@oe.utils.conditional('SECCONFIG', 'True', 'sec-config', '', d)} \
    ${@oe.utils.conditional('USB', 'True', 'usb', '', d)} \
"
RDEPENDS:${PN}-vm = " "


DIAG ?= 'True'
DIAGROUTER ?= 'True'
QMIFRAMEWORK ?= 'True'
TFTP ?= 'True'
TIMESERVICES ?= 'True'

RDEPENDS:${PN} += " \
    ${@oe.utils.conditional('DIAG', 'True', 'diag', '', d)} \
    ${@oe.utils.conditional('DIAGROUTER', 'True', 'diag-router', '', d)} \
    ${@oe.utils.conditional('QMIFRAMEWORK', 'True', 'qmi-framework', '', d)} \
    ${@oe.utils.conditional('TFTP', 'True', 'tftp-server', '', d)} \
    ${@oe.utils.conditional('TIMESERVICES', 'True', 'time-services', '', d)} \
"

RDEPENDS:${PN}-vm += " \
    ${@oe.utils.conditional('DIAG', 'True', 'diag', '', d)} \
    ${@oe.utils.conditional('DIAGROUTER', 'True', 'diag-router', '', d)} \
    ${@oe.utils.conditional('QMIFRAMEWORK', 'True', 'qmi-framework', '', d)} \
"

RDEPENDS:${PN}-recovery += " \
    ${@oe.utils.conditional('DIAGROUTER', 'True', 'diag-router', '', d)} \
"
