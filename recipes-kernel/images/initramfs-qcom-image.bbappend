# Add additional packages needed as part of initrd

PACKAGE_INSTALL += " \
    e2fsprogs \
    e2fsprogs-e2fsck \
    e2fsprogs-mke2fs \
    e2fsprogs-resize2fs \
    e2fsprogs-tune2fs \
    ${VIRTUAL-RUNTIME_dev_manager} \
    os-release-initrd \
"
