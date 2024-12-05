SUMMARY = "initscripts"
PACKAGE_ARCH = "${TUNE_PKGARCH}"

inherit packagegroup

RDEPENDS:${PN} = " \
  initscripts-automount-sdcard \
  initscripts-post-boot \
  initscripts-log-restrict \
  initscripts-modem-start-stop \
"

RDEPENDS:${PN}:append:qcom-custom-bsp = " \
  initscripts-debug-config \
"
