SUMMARY = "initscripts"
PACKAGE_ARCH = "${TUNE_PKGARCH}"

inherit packagegroup

PR = "r1"

RDEPENDS:${PN} = " \
  initscripts-post-boot \
  initscripts-log-restrict \
"

