LICENSE = "BSD-3-Clause-Clear"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/BSD-3-Clause-Clear;md5=7a434440b651f4a472ca93716d01033a"

inherit overlayfs

SRC_URI += " file://mnt-overlay.mount"

OVERLAYFS_WRITABLE_PATHS[mnt-overlay] += "/var"
OVERLAYFS_MOUNT_POINT[mnt-overlay] = "/mnt/overlay"
OVERLAYFS_QA_SKIP[mnt-overlay] = "mount-configured"

do_install:append() {
    install -d ${D}${systemd_unitdir}/system/local-fs.target.wants
    install -d ${D}/mnt/overlay
    install -m 0644  ${WORKDIR}/mnt-overlay.mount ${D}${systemd_unitdir}/system/mnt-overlay.mount
    ln -sf ${systemd_unitdir}/system/mnt-overlay.mount ${D}${systemd_unitdir}/system/local-fs.target.wants/mnt-overlay.mount
}

FILES:${PN} += " ${systemd_unitdir}/* /mnt/*"

