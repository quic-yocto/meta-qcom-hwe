FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:"

SRC_URI:append = " \
    file://0003-Add-support-for-directories-instead-of-symbolic-link.patch \
    file://0004-Add-support-for-systemd-boot-bootloader.patch \
    file://0001-Add-support-systemd-boot-Automatic-boot-assessment.patch \
"
