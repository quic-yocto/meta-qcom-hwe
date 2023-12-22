FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:"

SRC_URI:append = " \
    file://0001-wayland-protocols-add-custom-position-support-in-xdg.patch \
"