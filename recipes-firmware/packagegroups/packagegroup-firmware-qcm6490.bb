SUMMARY = "Firmware packages for the qcm6490 machine"

inherit packagegroup

RRECOMMENDS:${PN} += " \
    firmware-qcm6490-msl \
"
