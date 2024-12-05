LICENSE = "Qualcomm-Technologies-Inc.-Proprietary"

inherit packagegroup

PROVIDES = "${PACKAGES}"

PACKAGES = " \
        packagegroup-qcom-perf \
        "

RDEPENDS:packagegroup-qcom-perf = "\
    qcom-perf-hal \
    "
