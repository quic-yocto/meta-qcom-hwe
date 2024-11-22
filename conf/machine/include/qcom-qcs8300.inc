DEFAULTTUNE = "armv8-2a-crypto"
require conf/machine/include/arm/arch-armv8-2a.inc

SOC_FAMILY = "qcs8300"
require conf/machine/include/qcom-base.inc

# Additional Kernel cmdline parameters for debug builds
DBG_CMDLINE = "${@oe.utils.conditional('DEBUG_BUILD','1','earlycon reboot=panic_warm page_owner=on qcom_scm.download_mode=1 slub_debug=FZP,zs_handle,zspage;FZPU','',d)}"

KERNEL_CMDLINE_EXTRA ?= "pcie_pme=nomsi net.ifnames=0 pci=noaer kpti=off kasan=off kasan.stacktrace=off swiotlb=128 ${DBG_CMDLINE} mitigations=auto kernel.sched_pelt_multiplier=4 rcupdate.rcu_expedited=1 rcu_nocbs=0-7 no-steal-acc arm64.nopauth"

MACHINE_ESSENTIAL_EXTRA_RRECOMMENDS += " \
    fastrpc \
    kernel-modules \
    pd-mapper \
    networkmanager-nmcli \
    qrtr \
    tqftpserv \
"

MACHINE_EXTRA_RDEPENDS += " \
    packagegroup-firmware-qcs8300 \
"

MACHINE_EXTRA_RRECOMMENDS:append:qcom-custom-bsp = " \
    qcom-subsystem-ramdump \
    trace-cmd \
    remote-debug-agent \
    securedsp-example \
"
