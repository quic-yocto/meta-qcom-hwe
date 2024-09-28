SOC_FAMILY = "qcs8300"
require conf/machine/include/qcom-common.inc

DEFAULTTUNE = "armv8-2a-crypto"
require conf/machine/include/arm/arch-armv8-2a.inc

require conf/machine/include/qcom-base.inc

# Additional Kernel cmdline parameters for debug builds
DBG_CMDLINE = "${@oe.utils.conditional('DEBUG_BUILD','1','reboot=panic_warm page_owner=on qcom_scm.download_mode=1 slub_debug=FZP,zs_handle,zspage; FZPU','',d)}"

# Additional Kernel cmdline parameters for performance optimizations
CONSOLE_CMDLINE = "${@oe.utils.conditional('PERFORMANCE_BUILD','1','console=','console=ttyMSM0,115200n8 earlycon',d)}"

KERNEL_CMDLINE_EXTRA ?= "root=/dev/disk/by-partlabel/system rw rootwait ${CONSOLE_CMDLINE} pcie_pme=nomsi net.ifnames=0 pci=noaer kpti=off kasan=off kasan.stacktrace=off swiotlb=128 ${DBG_CMDLINE} mitigations=auto kernel.sched_pelt_multiplier=4 rcupdate.rcu_expedited=1 rcu_nocbs=0-7 no-steal-acc"

# Disable serial console for performance
SERIAL_CONSOLES = "${@oe.utils.conditional('PERFORMANCE_BUILD','1','','115200;ttyMSM0',d)}"

MACHINE_ESSENTIAL_EXTRA_RRECOMMENDS += " \
    fastrpc \
    kernel-modules \
    pd-mapper \
    qrtr \
    tqftpserv \
"


MACHINE_EXTRA_RRECOMMENDS:append:qcom-custom-bsp = " \
    qcom-subsystem-ramdump \
    trace-cmd \
"

QCOM_BOOTIMG_ROOTFS ?= "/dev/disk/by-partlabel/system"
