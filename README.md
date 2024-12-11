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
