do_install:append:qcom() {
    sed -i 's|mount "$ostree_sysroot" /sysroot|mount -o inlinecrypt "$ostree_sysroot" /sysroot|g' ${D}/init
}
