SUMMARY = "QCOM securemsm propreitary packagegroup"

LICENSE = "Qualcomm-Technologies-Inc.-Proprietary"
PACKAGE_ARCH = "${MACHINE_ARCH}"

inherit packagegroup

PROVIDES = "${PACKAGES}"

PACKAGES = "${PN}"

RDEPENDS:${PN} += " \
  securemsm \
  securemsm-headers \
  securemsm-features \
  minkipc \
  start-scripts-qtee-supplicant-daemon \
  start-scripts-sfs-config \
  install-securemsm-modules \
  start-scripts-ssgtzd-daemon \
  start-scripts-qwesd-daemon \
  "
