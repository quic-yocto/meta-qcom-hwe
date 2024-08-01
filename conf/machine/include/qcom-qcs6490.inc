# Configurations and variables for QCS6490 SoC family.

SOC_FAMILY = "qcm6490"
require conf/machine/include/qcom-base.inc

KERNEL_CMDLINE_EXTRA ?= "pcie_pme=nomsi earlycon"
