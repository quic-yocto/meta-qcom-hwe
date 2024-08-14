## Introduction
OpenEmbedded/Yocto Project layer for Qualcomm based platforms.

This layers provides aditional recipes and machine configuration files for Qualcomm platform.

This layer depends on:

| URI    | Branch |
| -------- | ------- |
| https://git.yoctoproject.org/meta-qcom | master |
| https://github.com/openembedded/meta-openembedded | master |
| https://git.yoctoproject.org/poky | master |
| https://github.com/quic-yocto/meta-qcom-distro | main |

## Machine Support
Supported devices are QCS6490-RB3Gen2 core-kit, QCM6490 idp and sa8775p ride-sx.

To add a new machine, introduce a new machine configuration file at `layers/meta-qcom-hwe/conf/machine/`

## Build an image

Clone this layer and it's dependent layers:
```
git clone https://github.com/quic-yocto/meta-qcom-hwe.git -b main
git clone git://git.yoctoproject.org/meta-qcom -b master
git clone https://github.com/quic-yocto/meta-qcom-distro.git -b main
git clone https://github.com/openembedded/meta-openembedded.git -b master
git clone git://git.yoctoproject.org/poky -b master
```

Initialize build environment:
```
source poky/oe-init-build-env
```
The script will cd into the newly created ```build/``` folder.

Update ```BBLAYERS``` in ```conf/bblayers.conf``` as shown below::
```
BBLAYERS ?= " \
  <WORKSPACE>/meta-qcom \
  <WORKSPACE>/meta-qcom-distro \
  <WORKSPACE>/meta-qcom-hwe \
  <WORKSPACE>/meta-openembedded/meta-python \
  <WORKSPACE>/meta-openembedded/meta-oe \
  <WORKSPACE>/poky/meta \
  "
```
```<WORKSPACE>``` is a place holder. Please use appropriate absolute path in place of ```<WORKSACE>```.


Update ```MACHINE``` and ```DISTRO``` variables into ```conf/local.conf``` to:
```
MACHINE = "qcs6490-rb3gen2-core-kit"
DISTRO = "qcom-wayland"
```

Build an image:
```
bitbake esp-qcom-image && bitbake qcom-console-image
```
This command generates ```esp``` (esp-qcom-image-<MACHINE>.rootfs-<timestamp>.vfat) and ```rootfs``` (qcom-console-image-<MACHINE>.rootfs-<timestamp>.ext4) images.

```qcom-wayland``` DISTRO and ```qcom-console-image``` are defined in meta-qcom-distro layer.

## Maintainer(s)
1. Naveen Kumar <quic_kumarn@quicinc.com>
2. Sourabh Banerjee <quic_sbanerje@quicinc.com>
3. Viswanath Kraleti <quic_vkraleti@quicinc.com>
