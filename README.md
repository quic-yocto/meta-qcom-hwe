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

## Steps for Creating nodistro
```bash
export SHELL=/bin/bash
sudo apt-get install python3-pip
python3 -m pip install --user future pyyaml requests
mkdir myworkspace
cd myworkspace
mkdir layers
cd layers
git clone https://git.yoctoproject.org/poky -b kirkstone
git clone git://git.yoctoproject.org/meta-qcom -b kirkstone
git clone https://github.com/quic-yocto/meta-qcom-hwe -b kirkstone
git clone https://github.com/openembedded/meta-openembedded.git -b kirkstone
cd ..
source layers/poky/oe-init-build-env
# If everything goes fine, you would be moved to myworkspace/build directory
bitbake --help # There should be a positive output
```

### Changes in Configuration Files
```bash
# changes in /myworkspace/build/conf/bblayers.conf
BBLAYERS ?= " \
  /myworkspace/layers/meta-qcom-hwe \
  /myworkspace/layers/meta-qcom \
  /myworkspace/layers/poky/meta \
  /myworkspace/layers/meta-openembedded/meta-python \
  /myworkspace/layers/meta-openembedded/meta-oe \
  "

# changes in /myworkspace/build/conf/local.conf
MACHINE ??= "qcm6490"
DISTRO = "nodistro"
INITRAMFS_IMAGE ="initramfs-qcom-image"
INITRAMFS_IMAGE_BUNDLE ?= "1"
DISTRO_FEATURES = "systemd"

# changes in /myworkspace/layers/meta-qcom/recipes-test/images/initramfs-tiny-image.bb
inherit core-image image-efi
```

## Flashing the device
After the build is complete, you will find the image file `efi.bin` under `/myworkspace/build/tmp-glibc/deploy/images/qcm6490`. Use `fastboot` to flash the image on the device.

## Maintainer(s)
1. Naveen Kumar <quic_kumarn@quicinc.com>
2. Sourabh Banerjee <quic_sbanerje@quicinc.com>