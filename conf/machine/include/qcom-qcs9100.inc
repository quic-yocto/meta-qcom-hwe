SOC_FAMILY ?= "qcs9100"
require conf/machine/include/qcom-common.inc

# Set prebuilt pkgs default arch
PBT_ARCH ?= "qcs9100"
require conf/machine/include/qcom-prebuilts.inc

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
# PREFERRED_PROVIDER_virtual/bootbins ?= "firmware-qcom-bootbins"

# Recipe provider for disk partition configuration
# PREFERRED_PROVIDER_virtual/partconf ?= "firmware-qcom-partconf"

# Fastboot expects an ext4 image, which needs to be 4096 aligned
IMAGE_FSTYPES = "ext4"
IMAGE_ROOTFS_ALIGNMENT = "4096"

EXTRA_IMAGECMD:ext4 = "-i 4096 -b 4096"
# For module signing
INHERIT += " qmodule"

MACHINE_ESSENTIAL_EXTRA_RRECOMMENDS += " \
    pd-mapper \
    qrtr \
    tqftpserv \
"

MACHINE_EXTRA_RRECOMMENDS += " \
    fastrpc \
"
