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
