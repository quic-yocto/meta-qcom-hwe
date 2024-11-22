DEFAULTTUNE = "armv8-2a-crypto"
require conf/machine/include/arm/arch-armv8-2a.inc

SOC_FAMILY = "qcs615"
require conf/machine/include/qcom-base.inc

# Additional Kernel cmdline parameters for debug builds
DBG_CMDLINE = "${@oe.utils.conditional('DEBUG_BUILD','1','earlycon nokaslr reboot=panic_warm page_owner=on qcom_scm.download_mode=1 slub_debug=FZP,zs_handle,zspage;FZPU','',d)}"

KERNEL_CMDLINE_EXTRA ?= "pcie_pme=nomsi net.ifnames=0 pci=noaer kpti=off kasan=off kasan.stacktrace=off swiotlb=128 ${DBG_CMDLINE} mitigations=auto kernel.sched_pelt_multiplier=4 rcupdate.rcu_expedited=1 rcu_nocbs=0-7 no-steal-acc vfio_iommu_type1.allow_unsafe_interrupts=1"

MACHINE_ESSENTIAL_EXTRA_RRECOMMENDS += " \
    kernel-modules \
    networkmanager-nmcli \
    pd-mapper \
    qrtr \
    tqftpserv \
"

MACHINE_EXTRA_RDEPENDS += " \
    packagegroup-firmware-qcs615 \
"

MACHINE_EXTRA_RRECOMMENDS:append:qcom-custom-bsp = " \
    fastrpc \
    qcom-subsystem-ramdump \
    trace-cmd \
"
