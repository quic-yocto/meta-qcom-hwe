FILESEXTRAPATHS:prepend := "${THISDIR}/files:"

SRC_URI:append:qcom = " \
    file://0001-libdrm-Export-libdrm_macros.h-header.patch \
"

PACKAGECONFIG = "libkms omap etnaviv install-test-programs nouveau"
