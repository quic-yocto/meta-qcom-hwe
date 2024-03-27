SUMMARY = "Linux kernel for SVM"
DESCRIPTION = "Recipe to build SVM Linux kernel"
LICENSE = "BSD-3-Clause-Clear"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/BSD-3-Clause-Clear;md5=7a434440b651f4a472ca93716d01033a"

inherit kernel 
PROVIDES:remove = "virtual/kernel"
KERNEL_PACKAGE_NAME = "linux-svm-kernel-qcom-package"

SRC_URI += "git://git.codelinaro.org/clo/la/kernel/qcom.git;protocol=https;rev=ad1a409fb8c0cf4f4b1af0d490ab05c62ab4abad;branch=kernel.qclinux.1.0.r1-rel"
S = "${WORKDIR}/git"

INITRAMFS_IMAGE = "svm-initramfs-qcom-image"

KERNEL_DEFCONFIG = "${S}/arch/arm64/configs/qcom_vm_defconfig"
KERNEL_CONFIG_FRAGMENTS:append = " ${@oe.utils.vartrue('DEBUG_BUILD', '${S}/arch/arm64/configs/qcom_vm_debug.config', '', d)}"

INITRAMFS_DEPLOY_DIR_IMAGE = "${DEPLOY_DIR_IMAGE}/${INITRAMFS_IMAGE}"

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

FILES:${PN} += "/boot/*"
FILES:${PN}-dbg = "/usr/lib/debug /usr/src/debug"
