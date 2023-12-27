## Introduction
OpenEmbedded/Yocto Project layer for Qualcomm based platforms.

This layers provides aditional recipes and machine configuration files for Qualcomm platform.

This layer depends on:

| URI    | Branch |
| -------- | ------- |
| https://git.yoctoproject.org/meta-qcom | kirkstone |
| https://github.com/openembedded/meta-openembedded | kirkstone |
| https://git.yoctoproject.org/poky | kirkstone |
| https://git.yoctoproject.org/meta-security | kirkstone |
| https://git.yoctoproject.org/meta-virtualization | kirkstone |
| https://git.yoctoproject.org/meta-selinux | kirkstone |
| https://github.com/quic-yocto/meta-qcom-hwe | kirkstone |
| https://github.com/quic-yocto/meta-qcom-distro | kirkstone |

## Steps for creating a nodistro build
Follow up to the section "Download the Yocto Project BSP"
in the README at https://github.com/quic-yocto/qcom-manifest

### Run setup-environment
```bash
MACHINE="qcm6490" DISTRO="nodistro" source setup-environment
```
After the above command executes the directory chnages to "build-nodistro".

### Changes in Configuration Files
```bash
# changes in build-nodistro/conf/local.conf
# Add following lines at end of the file.
DISTRO_FEATURES:append = " systemd pam overlayfs"
INITRAMFS_IMAGE = "initramfs-qcom-image"
INITRAMFS_IMAGE_BUNDLE ?= "1"

INIT_MANAGER = "systemd"
VIRTUAL-RUNTIME_init_manager = "systemd"
VIRTUAL-RUNTIME_dev_manager  = "udev"
```

### Building Image
```bash
bitbake qcom-minimal-image
```

### Flashing the device
Flash the images to device using qdl tool as per the steps in main documentation.

### Image recipes suported with 'nodistro'
Currently only qcom-minimal-image supports 'nodistro'.

## Maintainer(s)
1. Naveen Kumar <quic_kumarn@quicinc.com>
2. Sourabh Banerjee <quic_sbanerje@quicinc.com>
