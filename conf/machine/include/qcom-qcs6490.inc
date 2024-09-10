SOC_FAMILY = "qcm6490"
require conf/machine/include/qcom-common.inc

DEFAULTTUNE = "armv8-2a-crypto"
require conf/machine/include/arm/arch-armv8-2a.inc

require conf/machine/include/qcom-base.inc

# Additional Kernel cmdline parameters for debug builds
DBG_CMDLINE = "${@oe.utils.conditional('DEBUG_BUILD','1','page_owner=on qcom_scm.download_mode=1 slub_debug=FZP,zs_handle,zspage; FZPU','',d)}"

# Additional Kernel cmdline parameters for performance optimizations
CONSOLE_CMDLINE = "${@oe.utils.conditional('PERFORMANCE_BUILD','1','console=','console=ttyMSM0,115200n8 earlycon',d)}"

KERNEL_CMDLINE_EXTRA ?= "root=/dev/disk/by-partlabel/system rw rootwait ${CONSOLE_CMDLINE} pcie_pme=nomsi kernel.sched_pelt_multiplier=4 rcupdate.rcu_expedited=1 rcu_nocbs=0-7 kpti=off kasan=off kasan.stacktrace=off no-steal-acc ${DBG_CMDLINE} swiotlb=128 mitigations=auto"

# Disable serial console for performance
SERIAL_CONSOLES = "${@oe.utils.conditional('PERFORMANCE_BUILD','1','','115200;ttyMSM0',d)}"

MACHINE_ESSENTIAL_EXTRA_RRECOMMENDS += " \
    kernel-modules \
    modemmanager \
    pd-mapper \
"

MACHINE_EXTRA_RDEPENDS += " \
    packagegroup-firmware-qcm6490 \
"

MACHINE_EXTRA_RRECOMMENDS:append:qcom-custom-bsp = " \
    fastrpc \
    kernel-module-wlan-platform \
    kernel-module-qcacld-wlan \
    packagegroup-qcom-sensors \
    qcom-subsystem-ramdump \
    securedsp-example \
    wlan-conf \
"

QCOM_BOOTIMG_ROOTFS ?= "/dev/disk/by-partlabel/system"
