SUMMARY = "QCOM Audio Package Group"

LICENSE = "BSD-3-Clause"

PACKAGE_ARCH = "${MACHINE_ARCH}"

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
    pulseaudio-module-role-cork \
    pulseaudio-module-role-exclusive \
    pulseaudio-module-role-ignore \
    pulseaudio-module-qal-card \
    pulseaudio-module-qal-voiceui-card \
"

RDEPENDS:${PN}:append = ' \
    tinyalsa \
    tinycompress \
    agm \
    args \
    pal \
    audio-ftm \
    audioroute \
    mm-audio-headers \
    acdbdata \
    audio-node \
    pa-bt-audio \
    sva-capi-uv-wrapper \
    sva-cnn \
    sva-listen-sound-model \
    sva-eai \
    pa-pal-voiceui \
    ${PULSEAUDIO_PKGS}  \
'
