PACKAGE_INSTALL = " \
    base-passwd \
    bash \
    initramfs-module-copy-modules \
    initramfs-module-rootfs \
    initramfs-module-udev \
    packagegroup-qcom-boot \
    ${VIRTUAL-RUNTIME_base-utils} \
    ${MACHINE_ESSENTIAL_EXTRA_RDEPENDS} \
    ${ROOTFS_BOOTSTRAP_INSTALL} \
"
PACKAGE_INSTALL += "packagegroup-qcom-boot"
# Do not pollute the initrd image with rootfs features
IMAGE_FEATURES = "debug-tweaks"
IMAGE_LINGUAS = ""

LICENSE = "BSD-3-Clause-Clear"

IMAGE_FSTYPES = "${INITRAMFS_FSTYPES}"
inherit core-image
IMAGE_ROOTFS_SIZE = "8192"
IMAGE_ROOTFS_EXTRA_SPACE = "0"

# Exclude all kernel images from the rootfs
PACKAGE_EXCLUDE = "kernel-image-*"

do_image_complete[sstate-outputdirs] = "${DEPLOY_DIR_IMAGE}/${PN}"

# python method
do_rootfs:append() {
    image_rootfs = d.getVar('IMAGE_ROOTFS')
    rootfs_script = image_rootfs + '/init.d/90-rootfs'
    import subprocess
    subprocess.call(['sed', '-i', '/rootfs_run/i/bin/bash', rootfs_script])
}
