SUMMARY = "IOT Base Utilities Packagegroup"
LICENSE = "Qualcomm-Technologies-Inc.-Proprietary"

PROVIDES = "${PACKAGES}"
PACKAGE_ARCH = "${SOC_ARCH}"

inherit packagegroup

PACKAGES = " \
      packagegroup-qcom-iot-base-utils \
    "

RDEPENDS:packagegroup-qcom-iot-base-utils:qcom-custom-bsp = " \
      qcom-ib2c \
      qcom-video-ctrl \
    "
