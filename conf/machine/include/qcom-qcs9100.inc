# Configurations and variables for QCS9100 SoC family.

SOC_FAMILY ?= "qcs9100"
require conf/machine/include/qcom-base.inc

KERNEL_CMDLINE_EXTRA ?= "pci=noaer pcie_pme=nomsi earlycon"
