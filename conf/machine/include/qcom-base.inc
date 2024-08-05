# Common configurations and variables for Qualcomm platforms.

require conf/machine/include/qcom-common.inc
DEFAULTTUNE = "armv8-2a-crypto"
require conf/machine/include/arm/arch-armv8-2a.inc

# Provider for linux kernel
PREFERRED_PROVIDER_virtual/kernel ?= "linux-yocto-dev"

KERNEL_IMAGETYPE ?= "Image.gz"

# QDL expects 4096 aligned ext4 image for flashing
IMAGE_FSTYPES = "ext4"
IMAGE_ROOTFS_ALIGNMENT = "4096"
EXTRA_IMAGECMD:ext4 = "-i 4096 -b 4096"

SERIAL_CONSOLE ?= "115200 ttyMSM0"

QCOM_BOOTIMG_ROOTFS ?= "/dev/disk/by-partlabel/system"

