SUMMARY = "Firmware packages for the Dragonboard APQ8074 board"

inherit packagegroup

RRECOMMENDS:${PN} += " \
    ${@bb.utils.contains('DISTRO_FEATURES', 'opengl', 'linux-firmware-qcom-adreno-a3xx', '', d)} \
    linux-firmware-qcom-apq8074-audio \
    linux-firmware-qcom-apq8074-modem \
    linux-firmware-qcom-apq8074-wifi \
"
