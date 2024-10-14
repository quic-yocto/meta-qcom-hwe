SUMMARY = "QCOM Audio Package Group"

LICENSE = "BSD-3-Clause"

PACKAGE_ARCH = "${SOC_ARCH}"

inherit packagegroup

PROVIDES = "${PACKAGES}"

PACKAGES = "${PN}"

PULSEAUDIO_PKGS = " \
    pulseaudio-server \
    pulseaudio-module-loopback \
    pulseaudio-module-null-source \
    pulseaudio-module-combine-sink \
    pulseaudio-module-switch-on-port-available \
    pulseaudio-misc \
    pulseaudio-module-dbus-protocol \
    pulseaudio-module-bluetooth-discover \
    pulseaudio-module-bluetooth-policy \
    pulseaudio-module-bluez5-discover \
    pulseaudio-module-bluez5-device \
"

PULSEAUDIO_PKGS:append:qcom-custom-bsp = " \
    pulseaudio-module-pal-card \
    pulseaudio-module-pal-voiceui-card \
"

RDEPENDS:${PN}:append:qcom-custom-bsp = ' \
    tinyalsa \
    tinycompress \
    qcom-agm \
    qcom-args \
    qcom-pal \
    qcom-audio-ftm \
    qcom-audioroute \
    qcom-acdbdata \
    qcom-audio-node \
    qcom-kvh2xml \
    qcom-pa-bt-audio \
    qcom-audio-systems \
    qcom-vui-interface \
    qcom-sva-eai \
    qcom-pa-pal-voiceui \
    qcom-pa-pal-acd \
    qcom-audio-dac \
    qcom-audio-expander \
    qcom-audio-mercury \
    qcom-audio-plugin-headers \
    qcom-dac-mer-testapp \
    qcom-dac-plugin \
    qcom-mercury-plugin \
    ${PULSEAUDIO_PKGS}  \
'
