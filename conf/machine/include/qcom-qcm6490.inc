SOC_FAMILY = "qcm6490"
require conf/machine/include/qcom-common.inc

DEFAULTTUNE = "armv8-2a-crypto"
require conf/machine/include/arm/arch-armv8-2a.inc

PREFERRED_PROVIDER_virtual/egl      = "adreno"
PREFERRED_PROVIDER_virtual/libgl    = "adreno"
PREFERRED_PROVIDER_virtual/libgles1 = "adreno"
PREFERRED_PROVIDER_virtual/libgles2 = "adreno"
PREFERRED_PROVIDER_virtual/egl-native      = "mesa-native"
PREFERRED_PROVIDER_virtual/libgl-native    = "mesa-native"
PREFERRED_PROVIDER_virtual/libgles1-native = "mesa-native"
PREFERRED_PROVIDER_virtual/libgles2-native = "mesa-native"

PREFERRED_PROVIDER_virtual/kernel ?= "linux-kernel-qcom"

# Recipe provider for boot critical nhlos bins
PREFERRED_PROVIDER_virtual/bootbins ?= "firmware-qcm6490-boot"

# Fastboot expects an ext4 image, which needs to be 4096 aligned
IMAGE_FSTYPES = "ext4"
IMAGE_ROOTFS_ALIGNMENT = "4096"

EXTRA_IMAGECMD:ext4 = "-i 4096 -b 4096"

MACHINE_ESSENTIAL_EXTRA_RRECOMMENDS += " \
    modemmanager \
    pd-mapper \
    qrtr \
    tqftpserv \
"

MACHINE_EXTRA_RRECOMMENDS += " \
    fastrpc \
    subsystem-ramdump \
"
