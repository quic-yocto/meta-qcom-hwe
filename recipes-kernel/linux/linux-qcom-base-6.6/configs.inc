#Patches to be applied for kernel configs
FILESEXTRAPATHS:prepend := "${THISDIR}/configs:"


SRC_URI:append = " \
    file://qcs9100/0001-BACKPORT-arm64-defconfig-enable-SHM-Bridge-support-f.patch \
    "
