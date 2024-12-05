LICENSE = "Qualcomm-Technologies-Inc.-Proprietary"

PACKAGE_ARCH = "${MACHINE_ARCH}"

inherit packagegroup

PROVIDES = "${PACKAGES}"

PACKAGES = "${PN}"

# Sensors Image and Debugging utilities
RDEPENDS:${PN}:qcom-custom-bsp = "\
    qcom-sensinghub \
    qcom-sensors-registry \
    qcom-sensors-utils    \
    qcom-sensors-lookup \
    qcom-sensors-api \
    qcom-sensors-core \
    qcom-sensors-services \
    qcom-sensors-test-utils \
    qcom-sensors-test-core \
    qcom-sensors-test-apps \
"
