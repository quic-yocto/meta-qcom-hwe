SUMMARY = "IOT Base Utilities Packagegroup"
LICENSE = "Qualcomm-Technologies-Inc.-Proprietary"

PROVIDES = "${PACKAGES}"
PACKAGE_ARCH = "${MACHINE_ARCH}"

inherit packagegroup

PACKAGES = " \
      packagegroup-qcom-iot-base-utils \
    "

RDEPENDS:packagegroup-qcom-iot-base-utils = " \
      qti-ib2c \
    "
