LICENSE = "BSD-3-Clause-Clear"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/BSD-3-Clause-Clear;md5=7a434440b651f4a472ca93716d01033a"

SRC_URI += " file://var-persist.mount"

do_install() {
    install -d ${D}${systemd_unitdir}/system/local-fs.target.wants
    install -m 0644  ${WORKDIR}/var-persist.mount ${D}${systemd_unitdir}/system/var-persist.mount

    #if selinux is enabled we have to update the rootcontext /context for mounted folder
        if ${@bb.utils.contains('DISTRO_FEATURES','selinux','true', 'false', d)}; then
                sed -i 's/ext4/ext4\nOptions=rootcontext=system_u:object_r:qcom_persist_t:s0\n/' ${D}${systemd_unitdir}/system/var-persist.mount
        fi

    ln -sf ${systemd_unitdir}/system/var-persist.mount ${D}${systemd_unitdir}/system/local-fs.target.wants/var-persist.mount
}

FILES:${PN} += " ${systemd_unitdir}/*"
