# meta-qcom-hwe

![merge main](https://github.com/quic-yocto/meta-qcom-hwe/actions/workflows/merge-main.yml/badge.svg?branch=main)

## Introduction

OpenEmbedded/Yocto Project hardware enablement layer for Qualcomm based platforms.

This layers provides additional recipes and machine configuration files for Qualcomm platforms.

This layer depends on:

```
URI: https://github.com/openembedded/openembedded-core.git
layers: meta
branch: master
revision: HEAD

URI: https://github.com/Linaro/meta-qcom.git
branch: master
revision: HEAD
```

## Branches

- **main:** Primary development branch, with focus on upstream support and compatibility with the most recent Yocto Project release.
- **kirkstone:** Qualcomm Linux 1.x, aligned with Yocto Project 4.0 (LTS).

## Machine Support

See `conf/machine` for the complete list of supported devices.

## Quick build
Please refer to the [Yocto Project Quick Start Guide](https://docs.yoctoproject.org/brief-yoctoprojectqs/index.html) to set up your Yocto Project build environment.

1. Clone this layer and its dependencies
	```
	git clone https://github.com/quic-yocto/meta-qcom-hwe.git -b main
	git clone git://git.yoctoproject.org/meta-qcom -b master
	git clone git://git.yoctoproject.org/poky -b master
	```

2. Initialize the build environment:
	```
	source poky/oe-init-build-env
	```

3. Add `meta-qcom` and `meta-qcom-hwe` layers to bblayers.conf:
	```
	bitbake-layers add-layer ../meta-qcom
	bitbake-layers add-layer ../meta-qcom-hwe
	```

4. Set `MACHINE` in local.conf to one of the supported boards
	```
	MACHINE = "qcs6490-rb3gen2-core-kit"
	```

5. Set `INITRAMFS_IMAGE` in local.conf
	```
	INITRAMFS_IMAGE ?= "initramfs-qcom-image"
	```

6. Build images:
	```
	bitbake esp-qcom-image core-image-minimal
	```

	This command generates kernel and rootfs images in the formats `esp-qcom-image-[MACHINE].rootfs-[timestamp].vfat` and `core-image-minimal-[MACHINE].rootfs-[timestamp].ext4`, respectively.

## Quick build with kas
1. Install kas tool
	```
	sudo pip3 install kas
	```
2. Clone meta-qcom-hwe layer
	```
	git clone https://github.com/quic-yocto/meta-qcom-hwe.git -b main
	```
3. Build using the KAS configuration for one of the supported boards
	```
	kas build meta-qcom-hwe/ci/qcs6490-rb3gen2-core-kit.yml
	```

For more details, please visit [KAS documentation](https://kas.readthedocs.io/en/latest/index.html).

## Contributing

Please submit any patches against the `meta-qcom-hwe` layer (branch **main**) by using the GitHub pull-request feature. Fork the repo, create a branch, do the work, rebase from upstream, and create the pull request.

For some useful guidelines when submitting patches, please refer to:
https://docs.yoctoproject.org/dev/contributor-guide/submit-changes.html#preparing-changes-for-submission

Pull requests will be discussed within the GitHub pull-request infrastructure.

Branch **kirkstone** is not open for direct contributions, please raise an issue with the suggested change instead.

## Communication

- **GitHub Issues:** [meta-qcom-hwe issues](https://github.com/quic-yocto/meta-qcom-hwe/issues)
- **Pull Requests:** [meta-qcom-hwe pull requests](https://github.com/quic-yocto/meta-qcom-hwe/pulls)

## Maintainer(s)

* Naveen Kumar <quic_kumarn@quicinc.com>
* Sourabh Banerjee <quic_sbanerje@quicinc.com>
* Viswanath Kraleti <quic_vkraleti@quicinc.com>
* Ricardo Salveti <quic_rsalveti@quicinc.com>

## License

This layer is licensed under the MIT license. Check out [COPYING.MIT](COPYING.MIT) for more detais.
