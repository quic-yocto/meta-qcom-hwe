SUMMARY = "Linux kernel for SVM"
DESCRIPTION = "Recipe to build SVM Linux kernel"
LICENSE = "BSD-3-Clause-Clear"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/BSD-3-Clause-Clear;md5=7a434440b651f4a472ca93716d01033a"

inherit kernel
PROVIDES:remove = "virtual/kernel"
KERNEL_PACKAGE_NAME = "linux-svm-kernel-qcom-package"
FILESEXTRAPATHS:prepend = "${WORKSPACE}:"
SRC_URI = "git://git.codelinaro.org/clo/la/kernel/qcom.git;protocol=https;rev=350dfd604d2ffbe0cac99bf3459b49114aad11f4;branch=kernel.qclinux.1.0.r1-rel;destsuffix=kernel/kernel_platform/kernel"

KERNEL_DEFCONFIG = "${S}/arch/arm64/configs/qcom_vm_defconfig"
KERNEL_CONFIG_FRAGMENTS:append = " ${@oe.utils.vartrue('DEBUG_BUILD', '${S}/arch/arm64/configs/qcom_vm_debug.config', '', d)}"

INITRAMFS_DEPLOY_DIR_IMAGE = "${DEPLOY_DIR_IMAGE}"

S = "${WORKDIR}/kernel/kernel_platform/kernel"
kernel_conf_variable() {
    sed -e "/CONFIG_$1[ =]/d;" -i ${B}/.config
    if test "$2" = "n"
    then
        echo "# CONFIG_$1 is not set" >> ${B}/.config
    else
        echo "CONFIG_$1=$2" >> ${B}/.config
    fi
}

do_configure:prepend() {
    if [ -f '${WORKDIR}/defconfig' ]; then
        cp '${WORKDIR}/defconfig' '${B}/.config'
    else
        cp '${KERNEL_DEFCONFIG}' '${B}/.config'
    fi

    kernel_conf_variable LOCALVERSION "\"${LOCALVERSION}\""
    kernel_conf_variable LOCALVERSION_AUTO y

    if [ "${SCMVERSION}" = "y" ]; then
        # Add GIT revision to the local version
        head=`git --git-dir=${S}/.git  rev-parse --verify --short HEAD 2> /dev/null`
        printf "%s%s" +g $head > ${B}/.scmversion
    fi

    # Check for kernel config fragments.  The assumption is that the config
    # fragment will be specified with the absolute path.  For example:
    #   * ${WORKDIR}/config1.cfg
    #   * ${S}/config2.cfg
    # Iterate through the list of configs and make sure that you can find
    # each one.  If not then error out.
    # NOTE: If you want to override a configuration that is kept in the kernel
    #       with one from the OE meta data then you should make sure that the
    #       OE meta data version (i.e. ${WORKDIR}/config1.cfg) is listed
    #       after the in kernel configuration fragment.
    # Check if any config fragments are specified.
    if [ ! -z "${KERNEL_CONFIG_FRAGMENTS}" ]
    then
        for f in ${KERNEL_CONFIG_FRAGMENTS}
        do
            # Check if the config fragment was copied into the WORKDIR from
            # the OE meta data
            if [ ! -e "$f" ]
            then
                echo "Could not find kernel config fragment $f"
                exit 1
            fi
        done

        # Now that all the fragments are located merge them.
        ( cd ${WORKDIR} && ${S}/scripts/kconfig/merge_config.sh -m -r -O ${B} ${B}/.config ${KERNEL_CONFIG_FRAGMENTS} 1>&2 )
    fi
}

do_deploy[noexec] = "1"

do_install[depends] = "${INITRAMFS_IMAGE}:do_image_complete"

do_install:append() {
    # only keep Image in ${D}/var/gunyah/
    cp ${D}/boot/${KERNEL_IMAGETYPE}-* ${WORKDIR}/Image
    rm -rf ${D}/*
    mkdir -p ${D}/var/gunyah/
    cp ${WORKDIR}/Image ${D}/var/gunyah/Image
   
    # extract initramfs
    do_bundle_initramfs
    rm -rf ${WORKDIR}/initramfs
    install -d ${WORKDIR}/initramfs
    cpio -idmv -D ${WORKDIR}/initramfs < ${WORKDIR}/build/usr/initramfs-qcom-image-${MACHINE}.cpio

    # don't switch to real rootfs
    sed -i '/rootfs_run/i/bin/sh' ${WORKDIR}/initramfs/init.d/90-rootfs
    
    rm -f ${WORKDIR}/initramfs.cpio
    rm -f ${WORKDIR}/initramfs.cpio.gz
    # create initramfs.cpio.gz
    cd ${WORKDIR}/initramfs
    find . -print0 | cpio --null -ov --format=newc > ${WORKDIR}/initramfs.cpio
    gzip -9 ${WORKDIR}/initramfs.cpio

    # install modified initrd to ${D}/var/gunyah/
    mv ${WORKDIR}/initramfs.cpio.gz ${D}/var/gunyah/initrd.img
}

PACKAGESPLITFUNCS:remove = "split_kernel_module_packages"

FILES:${KERNEL_PACKAGE_NAME} = "/var/*"
