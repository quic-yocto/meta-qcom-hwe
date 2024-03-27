S = "${WORKDIR}"

DESCRIPTION = "Recipe to pack svm kernel and initramfs into rootfs"

LICENSE = "BSD-3-Clause-Clear"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/BSD-3-Clause-Clear;md5=7a434440b651f4a472ca93716d01033a"

SVM_KERNEL = "linux-svm-kernel-qcom"
SVM_KERNEL_PACKAGE = "${SVM_KERNEL}-package"

SVM_INITRAMFS = "svm-initramfs-qcom-image"

SVM_INITRAMFS_IMG = "${SVM_INITRAMFS}-${MACHINE}.cpio.gz"

do_install[depends] = "${SVM_KERNEL}:do_deploy ${SVM_INITRAMFS}:do_image_complete"

do_install:append() {
    mkdir -p ${D}/var/gunyah
    install -D -m 0644 ${DEPLOY_DIR_IMAGE}/${SVM_KERNEL_PACKAGE}/Image  ${D}/var/gunyah/Image
    install -D -m 0644 ${DEPLOY_DIR_IMAGE}/${SVM_INITRAMFS}/${SVM_INITRAMFS_IMG} ${D}/var/gunyah/initrd.img
}

FILES:${PN} += "/var/gunyah/*"
PACKAGE_ARCH = "${MACHINE_ARCH}"
