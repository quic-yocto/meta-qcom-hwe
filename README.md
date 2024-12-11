# meta-qcom-hwe

[![Build Yocto](https://github.com/quic-yocto/meta-qcom-hwe/actions/workflows/build-yocto.yml/badge.svg?event=push)](https://github.com/quic-yocto/meta-qcom-hwe/actions/workflows/build-yocto.yml)
[![Nightly Build](https://github.com/quic-yocto/meta-qcom-hwe/actions/workflows/nightly-build.yml/badge.svg)](https://github.com/quic-yocto/meta-qcom-hwe/actions/workflows/nightly-build.yml)

## Introduction

OpenEmbedded/Yocto Project hardware enablement layer for Qualcomm based platforms.

This layers provides additional recipes and machine configuration files for
Qualcomm platforms.

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

* **main:** Primary development branch, with focus on upstream support and
  compatibility with the most recent Yocto Project release.
* **kirkstone:** Qualcomm Linux 1.x, aligned with Yocto Project 4.0 (LTS).

## Machine Support

See `conf/machine` for the complete list of supported devices.

## Quick build

Please refer to the [Yocto Project Reference Manual](https://docs.yoctoproject.org/ref-manual/system-requirements.html)
to set up your Yocto Project build environment.

Please follow the instructions below for a KAS-based build. The KAS tool offers
an easy way to setup bitbake based projects. For more details, visit the
[KAS documentation](https://kas.readthedocs.io/en/latest/index.html).

1. Install kas tool:

   ```
   sudo pip3 install kas
   ```

2. Clone `meta-qcom-hwe` layer:

   ```
   git clone https://github.com/quic-yocto/meta-qcom-hwe.git -b main
   ```

3. Build using the KAS configuration for one of the supported boards

   ```
   kas build meta-qcom-hwe/ci/qcs6490-rb3gen2-core-kit.yml
   ```

For a manual build without KAS, refer to the [Yocto Project Quick Build](https://docs.yoctoproject.org/brief-yoctoprojectqs/index.html).

## Flash

### Build QDL tool

QDL tool communicates with USB devices of id `05c6:9008` and uploads a flash
loader, which is then used for flashing images. Follow the steps below to
download and compile QDL for your platform:

1. Clone the QDL repository:

   ```
   git clone https://github.com/linux-msm/qdl
   ```

2. Read the README and install build dependencies (`libxml2-dev` and
   `libusb-1.0-0-dev`). On Debian-based distribution run:

   ```
   sudo apt install libxml2-dev libusb-1.0-0-dev
   ```

3. Build the QDL tool using make:

   ```
   cd qdl
   make
   ```

### Flash images

Make sure that ModemManager is not running, disable it if necessary.
Location of all DIP switches, USB debug port and buttons (`F_DL` for instance)
can be found on [RB3 Gen 2 Quick Start Guide](https://docs.qualcomm.com/bundle/publicresource/topics/80-70014-253/ubuntu_host.html).

1. Set up `DIP_SW_0` positions `1` and `2` to `ON`. This enables serial output
   to the debug port.
2. Connect the mini USB debug cable to the host. Baud rate should be `115200`.
   Check in `dmesg` how UART shows up (e.g. `/dev/ttyUSB0`):

   ```
   $ sudo dmesg | grep tty
   [217664.921039] usb 3-1.1.4: FTDI Serial Device converter attached to ttyUSB0
   ```

3. Use your favorite serial comminication program to access the console, such
   as minicom, picocom, putty etc. Baud rate should be 115200:

   ```
   picocom -b 115200 /dev/ttyUSB0
   ```

4. Plug in the USB-C cable from the host.
5. Unpack the tarball stored in the deploy directory:

   ```
   cd build/tmp/deploy/images/qcs6490-rb3gen2-core-kit/
   tar zxvf core-image-base-qcs6490-rb3gen2-core-kit.rootfs.qcomflash.tar.gz
   ```

6. Use the QDL tool (built in the previous section) to flash the images:

   ```
   qdl --debug prog_firehose_ddr.elf rawprogram*.xml patch*.xml
   ```

7. Press and hold the `F_DL` button and connect the power cable. The process
   of flashing should start:

   ```
   USB: using out-chunk-size of 1048576
   HELLO version: 0x2 compatible: 0x1 max_len: 1024 mode: 0
   READ64 image: 13 offset: 0x0 length: 0x40
   ```

## Contributing

Please submit any patches against the `meta-qcom-hwe` layer (branch **main**)
by using the GitHub pull-request feature. Fork the repo, create a branch, do
the work, rebase from upstream, and create the pull request.

For some useful guidelines when submitting patches, please refer to:
[Preparing Changes for Submission](https://docs.yoctoproject.org/dev/contributor-guide/submit-changes.html#preparing-changes-for-submission)

Pull requests will be discussed within the GitHub pull-request infrastructure.

Branch **kirkstone** is not open for direct contributions, please raise an
issue with the suggested change instead.

## Communication

* **GitHub Issues:** [meta-qcom-hwe issues](https://github.com/quic-yocto/meta-qcom-hwe/issues)
* **Pull Requests:** [meta-qcom-hwe pull requests](https://github.com/quic-yocto/meta-qcom-hwe/pulls)

## Maintainer(s)

* Naveen Kumar <quic_kumarn@quicinc.com>
* Sourabh Banerjee <quic_sbanerje@quicinc.com>
* Viswanath Kraleti <quic_vkraleti@quicinc.com>
* Ricardo Salveti <ricardo.salveti@oss.qualcomm.com>
* Nicolas Dechesne <nicolas.dechesne@oss.qualcomm.com>

## License

This layer is licensed under the MIT license. Check out [COPYING.MIT](COPYING.MIT)
for more detais.
