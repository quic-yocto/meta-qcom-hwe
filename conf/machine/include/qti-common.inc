SOC_FAMILY:prepend = "qcom:"
require conf/machine/include/soc-family.inc

PREFERRED_PROVIDER_virtual/kernel = "linux-kernel-qcom"

PREFERRED_PROVIDER_android-tools-conf = "android-tools-conf-configfs"

# Fastboot expects an ext4 image, which needs to be 4096 aligned
IMAGE_FSTYPES = "ext4"
IMAGE_ROOTFS_ALIGNMENT = "4096"

EXTRA_IMAGECMD:ext4 = "-i 4096 -b 4096"

# Boot image settings
QCOM_BOOTIMG_KERNEL_BASE ?= "0x80000000"
QCOM_BOOTIMG_PAGE_SIZE ?= "4096"

# Default serial console for QCOM devices
SERIAL_CONSOLES ?= "115200;ttyMSM0"

# Increase INITRAMFS_MAXSIZE to 384 MiB to cover initramfs-kerneltest-full
# image.
INITRAMFS_MAXSIZE = "393216"
