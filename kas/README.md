## Introduction
KAS configuration for Qualcomm based platforms.

## Compatible Yocto project BSP version
qcom-6.6.38-QLI.1.2-Ver.1.0

## KAS build instructions

To build with KAS, follow these steps:

1. The default BSP type for building the image is 'custom'. You can change it to 'base' by using the override file 'bsp-base.yml'.

2. It is mandatory to specify the machine configuration file from command line for building the image.

	Available list of machine configuration files:
	===============================================
	qcm6490-idp.yml
	qcs8300-ride-sx.yml
	qcs9100-ride-sx.yml
	qcs6490-rb3gen2-core-kit.yml
	qcs6490-rb3gen2-vision-kit.yml
	qcs6490-rb3gen2-industrial-kit.yml

4. To modify the default settings, use the environment variables provided below.

	KAS_BUILD_DIR: Set the build directory (default: build).
	KAS_DISTRO: Set the distribution (default: qcom-wayland).
	KAS_TARGET: Define the target (default: qcom-minimal-image).

	Valid Values for KAS_TARGET:
	==============================
	qcom-minimal-image: A minimal rootfs image that boots to shell. This is the default image.
	qcom-console-image: Boot to shell with Package group to bring in all basic packages.
	qcom-multimedia-image: Image recipe includes recipes for multimedia software components,
				such as audio, bluetooth, camera, computer vision, display and video.
	qcom-multimedia-test-image: This image recipe includes tests.


### Environment setup
Follow host setup instructions available at https://github.com/quic-yocto/qcom-manifest/tree/qcom-linux-kirkstone

### Download the source code
Create a workspace directory:
```bash
mkdir "my-workspace" && cd "$_"
```

If the meta-qcom-hwe layer hasn’t been downloaded yet, download it to `my-workspace` as it contains the KAS configuration.
```bash
git clone https://github.com/quic-yocto/meta-qcom-hwe -b kirkstone
```

### Build with KAS
```bash
kas build meta-qcom-hwe/kas/qcs6490-rb3gen2-core-kit.yml
```

### Few more build commands
```bash
KAS_BUILD_DIR=build-core-kit-custom KAS_TARGET=qcom-multimedia-image kas build meta-qcom-hwe/kas/qcs6490-rb3gen2-core-kit.yml
```
```bash
KAS_BUILD_DIR=build-vision-kit-base KAS_TARGET=qcom-console-image kas build meta-qcom-hwe/kas/qcs6490-rb3gen2-vision-kit.yml:meta-qcom-hwe/kas/bsp-base.yml
```

## Maintainer(s)
1. Naveen Kumar <quic_kumarn@quicinc.com>
2. Sourabh Banerjee <quic_sbanerje@quicinc.com>
3. Viswanath Kraleti <quic_vkraleti@quicinc.com>
