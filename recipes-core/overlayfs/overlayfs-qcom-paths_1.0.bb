LICENSE = "BSD-3-Clause-Clear"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/BSD-3-Clause-Clear;md5=7a434440b651f4a472ca93716d01033a"

inherit overlayfs

SRC_URI += " file://mnt-overlay.mount"
SRC_URI += " file://var-persist.mount"
SRC_URI += " file://remove-var-tmp-symlink.service"

OVERLAYFS_WRITABLE_PATHS[mnt-overlay] += "/opt /etc /var"
OVERLAYFS_MOUNT_POINT[mnt-overlay] = "/mnt/overlay"
OVERLAYFS_QA_SKIP[mnt-overlay] = "mount-configured"

do_install:append() {
    install -d ${D}${systemd_unitdir}/system/local-fs.target.wants
    install -d ${D}/mnt/overlay
    install -d ${D}/opt
    install -d ${D}/var/persist
    install -m 0644  ${WORKDIR}/mnt-overlay.mount ${D}${systemd_unitdir}/system/mnt-overlay.mount
    install -m 0644  ${WORKDIR}/var-persist.mount ${D}${systemd_unitdir}/system/var-persist.mount
    install -m 0644  ${WORKDIR}/remove-var-tmp-symlink.service ${D}${systemd_unitdir}/system/remove-var-tmp-symlink.service


#if selinux is enabled we have to update the rootcontext /context for mounted folder
        if ${@bb.utils.contains('DISTRO_FEATURES','selinux','true', 'false', d)}; then
                sed -i  's/Options=/Options=rootcontext=system_u:object_r:etc_t:s0,/' ${D}${systemd_unitdir}/system/mnt-overlay.mount
                sed -i 's/ext4/ext4\nOptions=rootcontext=system_u:object_r:qcom_persist_t:s0\n/' ${D}${systemd_unitdir}/system/var-persist.mount

        fi

    ln -sf ${systemd_unitdir}/system/mnt-overlay.mount ${D}${systemd_unitdir}/system/local-fs.target.wants/mnt-overlay.mount
    ln -sf ${systemd_unitdir}/system/var-persist.mount ${D}${systemd_unitdir}/system/local-fs.target.wants/var-persist.mount
    ln -sf ${systemd_unitdir}/system/remove-var-tmp-symlink.service ${D}${systemd_unitdir}/system/local-fs.target.wants/remove-var-tmp-symlink.service
}

FILES:${PN} += " ${systemd_unitdir}/* /mnt/* /opt /var/persist"

