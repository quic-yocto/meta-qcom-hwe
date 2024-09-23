#Define and add SOC_ARCH to the list of architectures compatible with the given target.
SOC_ARCH = "${@ d.getVar('SOC_FAMILY').replace('-', '_').replace('qcom:', '')}"
PACKAGE_EXTRA_ARCHS:append = "${@['', ' ${SOC_ARCH}']['${SOC_ARCH}' != "${MACHINE_ARCH}"]}"

# Supported BSP selections are: base, custom
# We intend to use 'custom' BSP by default.
# Specific MACHINEs or DISTROs might change this as needed.
QCOM_SELECTED_BSP ??= "custom"
MACHINEOVERRIDES =. "qcom-${QCOM_SELECTED_BSP}-bsp:"

# Set prebuilt pkgs default arch
PBT_ARCH ?= "${SOC_ARCH}"
require conf/machine/include/qcom-prebuilts.inc

# Provider for Graphics Library.
# qcom-base-bsp uses 'mesa' as GL provider
GL_PROVIDER ?= "qcom-adreno"
GL_PROVIDER:qcom-base-bsp ?= "mesa"

PREFERRED_PROVIDER_virtual/egl      = "${GL_PROVIDER}"
PREFERRED_PROVIDER_virtual/libgl    = "${GL_PROVIDER}"
PREFERRED_PROVIDER_virtual/libgles1 = "${GL_PROVIDER}"
PREFERRED_PROVIDER_virtual/libgles2 = "${GL_PROVIDER}"

PREFERRED_PROVIDER_virtual/egl-native      = "mesa-native"
PREFERRED_PROVIDER_virtual/libgl-native    = "mesa-native"
PREFERRED_PROVIDER_virtual/libgles1-native = "mesa-native"
PREFERRED_PROVIDER_virtual/libgles2-native = "mesa-native"

# Provider for libgbm
PREFERRED_PROVIDER_virtual/libgbm ?= "gbm"
PREFERRED_PROVIDER_virtual/libgbm:qcom-base-bsp ?= "mesa"

# Provider for linux kernel
# qcom-base-bsp uses 'linux-qcom-base' as kernel
PREFERRED_PROVIDER_virtual/kernel ?= "linux-qcom-custom"
PREFERRED_PROVIDER_virtual/kernel:qcom-base-bsp ?= "linux-qcom-base"

KERNEL_IMAGETYPE ?= "Image"
KERNEL_ALT_IMAGETYPE ?= "vmlinux"

# Recipe provider for boot critical nhlos bins
PREFERRED_PROVIDER_virtual/bootbins ?= "firmware-qcom-bootbins"

# Recipe provider for disk partition configuration
PREFERRED_PROVIDER_virtual/partconf ?= "firmware-qcom-partconf"

# Use qcom specific version of modemmanger in custom-bsp
PREFERRED_VERSION_modemmanager:qcom-custom-bsp ?= "1.22.0.qcom"
PREFERRED_VERSION_libqmi:qcom-custom-bsp ?= "1.34.0.qcom"
PREFERRED_VERSION_libqrtr-glib:qcom-custom-bsp ?= "1.2.0.qcom"

#Use qcom specific version of wpa-supplicant in custom-bsp
PREFERRED_VERSION_wpa-supplicant:qcom-custom-bsp ?= "2.10.qcom"

# For module signing
INHERIT += " qmodule"

# QDL expects 4096 aligned ext4 image for flashing
IMAGE_FSTYPES = "ext4"
IMAGE_ROOTFS_ALIGNMENT = "4096"
EXTRA_IMAGECMD:ext4 = "-i 4096 -b 4096"

# Mask out tar.gz from IMAGE_FSTYPES
IMAGE_TYPES_MASKED = "tar.gz"

SERIAL_CONSOLE ?= "115200 ttyMSM0"

#
# Below are variables are backport from meta/conf/image-uefi.conf master branch
#
EFI_ARCH = "aa64"

# Location of UKI inside EFI System Partition
EFI_UKI_DIR ?= "/EFI/Linux"
EFI_UKI_PATH = "${EFI_PREFIX}${EFI_UKI_DIR}"

# Use systemd-boot as the EFI bootloader
EFI_PROVIDER = "systemd-boot"

# Install packages at root of ESP
EFI_PREFIX = ""

# Location of dtb inside ESP
EFI_DTB_DIR ?= "${EFI_PREFIX}dtb"

# Unified Kernel Image (UKI) name
EFI_LINUX_IMG ?= "linux-${MACHINE}.efi"

# Place dtb at EFIDTDIR to seamlessly package
KERNEL_DTBDEST = "${EFI_DTB_DIR}"
