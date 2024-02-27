SUMMARY = "Firmware packages for the qcs8550 machine"

inherit packagegroup

RRECOMMENDS:${PN} += " \
    firmware-qcs8550-msl \
"
