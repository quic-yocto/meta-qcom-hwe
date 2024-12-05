do_install:append() {
    if [ -n ${OSTREE_KERNEL} ]; then
        if [ -n "${INITRAMFS_IMAGE}" ]; then
            # this is a hack for ostree not to override init= in kernel cmdline -
            # make it think that the initramfs is present (while it is in UKI image)
            rm -fv $kerneldir/initramfs.img
            touch $kerneldir/initramfs.img
        fi
    fi
}

do_install[depends] += "${@ 'linux-qcom-uki:do_deploy' if d.getVar('EFI_LINUX_IMG') else ''}"
