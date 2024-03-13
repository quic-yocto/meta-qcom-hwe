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
Follow up to the section "Download the Yocto Project BSP" in the README at https://github.com/quic-yocto/qcom-manifest

### Run setup-environment
```bash
MACHINE="qcm6490" DISTRO="nodistro" source setup-environment
```
The directory changes to `build-nodistro` after executing the above command.

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

### Flashing the images on the device
Flash the images on the device using qdl tool as per the steps in the main documentation.

### Image recipes suported with `nodistro`
Currently, only `qcom-minimal-image` supports `nodistro`.


## Adding a new machine
### Add Machine
To add a new machine introduce a new machine configuration file at `layers/meta-qcom-hwe/conf/machine/`, for example, `layers/meta-qcom-hwe/conf/machine/testBoard.conf`

```bash
#@TYPE: Machine
#@NAME: TestBoard
#@DESCRIPTION: Machine configuration for a development board, based on Qualcomm QCM6490

MACHINEOVERRIDES =. "qcm6490:"
require qcm6490.conf
```
Adding `MACHINEOVERRIDES` helps to re-use the configurations in recipes created for qcm6490.

### Build an image for the machine added
```bash
MACHINE="testBoard" DISTRO="qcom-wayland" source setup-environment
bitbake qcom-multimedia-image
```

## Known Issues

## Maintainer(s)
1. Naveen Kumar <quic_kumarn@quicinc.com>
2. Sourabh Banerjee <quic_sbanerje@quicinc.com>
3. Viswanath Kraleti <quic_vkraleti@quicinc.com>
