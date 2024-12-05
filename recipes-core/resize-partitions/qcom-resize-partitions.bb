SUMMARY = "Scripts to resize Disk partitions"

LICENSE = "BSD-3-Clause-Clear"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/BSD-3-Clause-Clear;md5=7a434440b651f4a472ca93716d01033a"

SRC_URI  +=  "file://resize-partition.service.in"

# Tied to systemd. Build it only when systemd is also building.
inherit features_check systemd
REQUIRED_DISTRO_FEATURES = "systemd"

do_configure[noexec] = "1"

do_install () {
  install -d ${D}${systemd_unitdir}/system/
  install -m 0644 ${WORKDIR}/resize-partition.service.in \
      ${D}${systemd_unitdir}/system/resize-partition@.service
  # enable the services
  install -d ${D}${systemd_unitdir}/system/local-fs-pre.target.wants/
  ln -sf ../resize-partition@.service \
      ${D}${systemd_unitdir}/system/local-fs-pre.target.wants/resize-partition@system.service
  ln -sf ../resize-partition@.service \
      ${D}${systemd_unitdir}/system/local-fs-pre.target.wants/resize-partition@persist.service
}

SYSTEMD_SERVICE:${PN} = "resize-partition@.service"

PACKAGE_ARCH = "${SOC_ARCH}"
FILES:${PN} += " ${systemd_unitdir}/system/* "
