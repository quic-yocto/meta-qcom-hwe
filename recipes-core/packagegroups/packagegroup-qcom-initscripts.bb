SUMMARY = "initscripts"
PACKAGE_ARCH = "${TUNE_PKGARCH}"

inherit packagegroup

RDEPENDS:${PN} = " \
  initscripts-post-boot \
  initscripts-log-restrict \
  initscripts-modem-start-stop \
  initscripts-debug-config \
"

