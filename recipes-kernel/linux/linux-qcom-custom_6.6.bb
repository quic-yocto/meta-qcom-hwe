SECTION = "kernel"

SUMMARY = "Linux kernel for QCOM devices"
DESCRIPTION = "Recipe to build Linux kernel"

LICENSE = "GPLv2.0-with-linux-syscall-note"
LIC_FILES_CHKSUM = "file://COPYING;md5=6bc538ed5bd9a7fc9398086aedcd7e46"

inherit kernel sota

COMPATIBLE_MACHINE = "(qcom)"

SRCPROJECT = "git://git.codelinaro.org/clo/la/kernel/qcom.git;protocol=https"
SRCBRANCH  = "kernel.qclinux.1.0.r1-rel"
SRCREV     = "46009bdf8e055c4bc6ef8fa5fcfcf79d8ba0c9a0"

SRC_URI = "${SRCPROJECT};branch=${SRCBRANCH};destsuffix=kernel \
           ${@bb.utils.contains('DISTRO_FEATURES', 'selinux', ' file://selinux.cfg', '', d)} \
           ${@bb.utils.contains('DISTRO_FEATURES', 'selinux', ' file://selinux_debug.cfg', '', d)} \
           "

S = "${WORKDIR}/kernel"

KERNEL_CONFIG ??= "qcom_defconfig"

KERNEL_CONFIG_FRAGMENTS:append = " ${S}/arch/arm64/configs/qcom_addons.config"
KERNEL_CONFIG_FRAGMENTS:append = " ${@oe.utils.vartrue('DEBUG_BUILD', '${S}/arch/arm64/configs/qcom_debug.config', '', d)}"
KERNEL_CONFIG_FRAGMENTS:append = " ${@oe.utils.vartrue('DEBUG_BUILD', '${S}/arch/arm64/configs/qcom_addons_debug.config', '', d)}"

# Enable selinux support
SELINUX_CFG = "${@oe.utils.vartrue('DEBUG_BUILD', 'selinux_debug.cfg', 'selinux.cfg', d)}"
KERNEL_CONFIG_FRAGMENTS:append = " ${@bb.utils.contains('DISTRO_FEATURES', 'selinux', '${WORKDIR}/${SELINUX_CFG}', '', d)}"

# List of kernel modules that will be auto-loaded for Qualcomm platforms.

# Coresight and stm modules for QDSS functions
KERNEL_MODULE_AUTOLOAD += "coresight coresight-tmc coresight-funnel"
KERNEL_MODULE_AUTOLOAD += "coresight-replicator coresight-etm4x coresight-stm"
KERNEL_MODULE_AUTOLOAD += "coresight-cti coresight-tpdm coresight-tpda coresight-dummy"
KERNEL_MODULE_AUTOLOAD += "coresight-remote-etm coresight-tgu"
KERNEL_MODULE_AUTOLOAD += "stm_core stm_p_ost stm_console stm_heartbeat stm_ftrace "

# IPA
KERNEL_MODULE_AUTOLOAD += "ipa"

# PHY
KERNEL_MODULE_AUTOLOAD += "at803x"

#Add DTC_FLAGS to compile DTB with symbols.
KERNEL_DTC_FLAGS += "-@"

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
    if [ ! -f "${S}/arch/${ARCH}/configs/${KERNEL_CONFIG}" ]; then
        bbfatal "KERNEL_CONFIG '${KERNEL_CONFIG}' was specified, but not present in the source tree"
    else
        cp '${S}/arch/${ARCH}/configs/${KERNEL_CONFIG}' '${B}/.config'
    fi

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

    kernel_conf_variable LOCALVERSION "\"${LOCALVERSION}\""
    kernel_conf_variable LOCALVERSION_AUTO y
}

do_configure:append() {
    oe_runmake -C ${S} O=${B} savedefconfig && cp ${B}/defconfig ${WORKDIR}/defconfig.saved
}

# Kernel 6.6 with commit d8131c2965d5ee59bfa4d548641e52a13cbe17c9
# removed $(MODLIB)/source symlink. However, kernel.bbclass is yet to
# catchup and failing while removing a non-existing link. To mitigate,
# added a dummy link which would be deleted by kernel_do_install task.
do_install:prepend() {
    install -d ${D}${nonarch_base_libdir}/modules/${KERNEL_VERSION}
    ln -rs ${STAGING_KERNEL_DIR} ${D}${nonarch_base_libdir}/modules/${KERNEL_VERSION}/source
}

do_package[nostamp] = "1"
