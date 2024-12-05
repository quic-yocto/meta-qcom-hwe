SUMMARY = "Firmware packages for the qcs9100 machine"

inherit packagegroup

RRECOMMENDS:${PN} += " \
    firmware-qcom-dspso \
    firmware-qcom-hlosfw \
"

RRECOMMENDS:${PN}:append:qcom-base-bsp = " \
    linux-firmware-qcom-vpu \
    linux-firmware-qcom-adreno-a663 \
"
