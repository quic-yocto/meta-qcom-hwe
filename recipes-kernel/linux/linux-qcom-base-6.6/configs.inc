#Patches to be applied for kernel configs
FILESEXTRAPATHS:prepend := "${THISDIR}/configs:"


SRC_URI:append = " \
    file://qcs9100/0001-BACKPORT-arm64-defconfig-enable-SHM-Bridge-support-f.patch \
    file://qcs615/0022-FROMLIST-arm64-defconfig-enable-clock-controller-int.patch \
    file://qcs8300/0024-FROMLIST-arm64-defconfig-enable-clock-controller-int.patch \
    file://qcs9100/0001-FROMLIST-arm64-defconfig-Enable-sa8775p-clock-contro.patch \
    file://qcs615/0011-FROMLIST-arm64-defconfig-Enable-QCS615-clock-control.patch \
    "
