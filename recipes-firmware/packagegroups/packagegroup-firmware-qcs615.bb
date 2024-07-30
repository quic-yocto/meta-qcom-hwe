SUMMARY = "Firmware packages for the qcs615 machine"

inherit packagegroup

RRECOMMENDS:${PN} += " \
    firmware-qcom-hlosfw \
"
