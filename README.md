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

## Adding a new machine
### Add Machine
To add a new machine introduce a new machine configuration file at `layers/meta-qcom-hwe/conf/machine/`, for example, `layers/meta-qcom-hwe/conf/machine/testboard.conf`

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
MACHINE="testboard" DISTRO="qcom-wayland" source setup-environment
bitbake qcom-multimedia-image
```
'qcom-wayland' DISTRO and 'qcom-multimedia-image' are defined in meta-qcom-distro layer.

### Flashing the images on the device
Flash the images on the device using qdl tool as per the steps in the main documentation.


## Known Issues


## Maintainer(s)
1. Naveen Kumar <quic_kumarn@quicinc.com>
2. Sourabh Banerjee <quic_sbanerje@quicinc.com>
3. Viswanath Kraleti <quic_vkraleti@quicinc.com>
