FILESEXTRAPATHS:prepend := "${THISDIR}/${BPN}:"

SRC_URI:append:qcom = "file://procps.conf"

do_install:append:qcom () {
    # For coredump handling
    install -d ${D}/${sysconfdir}/tmpfiles.d
    install -m 644 ${WORKDIR}/procps.conf ${D}/${sysconfdir}/tmpfiles.d/procps.conf

    echo "" >> ${D}${sysconfdir}/sysctl.conf
    echo "# Enable core dump collection" >> ${D}${sysconfdir}/sysctl.conf
    echo "kernel.core_pattern = /var/coredump/%e.core" >> ${D}${sysconfdir}/sysctl.conf
    echo "kernel.core_uses_pid = 0" >> ${D}${sysconfdir}/sysctl.conf
    echo "fs.suid_dumpable = 2" >> ${D}${sysconfdir}/sysctl.conf

    # update current console loglevel to 4 (KERN_WARNING)
    echo "" >> ${D}${sysconfdir}/sysctl.conf
    echo "# set current console loglevel to 4 (KERN_WARNING)" >> ${D}${sysconfdir}/sysctl.conf
    echo "kernel.printk=4" >> ${D}${sysconfdir}/sysctl.conf
}
