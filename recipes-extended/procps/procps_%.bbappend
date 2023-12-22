
do_install:append:qcom () {
    # For coredump handling
    mkdir -p ${D}/var/coredump
    echo "" >> ${D}${sysconfdir}/sysctl.conf
    echo "# Enable core dump collection" >> ${D}${sysconfdir}/sysctl.conf
    echo "kernel.core_pattern = /var/coredump/%e.core" >> ${D}${sysconfdir}/sysctl.conf
    echo "kernel.core_uses_pid = 0" >> ${D}${sysconfdir}/sysctl.conf
    echo "fs.suid_dumpable = 2" >> ${D}${sysconfdir}/sysctl.conf
}
