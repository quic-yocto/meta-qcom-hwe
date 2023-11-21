FILESEXTRAPATHS:append := "${THISDIR}/${BPN}:"

# Enable selinux support in the kernel if the feature is enabled
SRC_URI += "${@bb.utils.contains('DISTRO_FEATURES', 'selinux', 'file://selinux.cfg', '', d)}"
KERNEL_CONFIG_FRAGMENTS += "${@bb.utils.contains('DISTRO_FEATURES', 'selinux', '${WORKDIR}/selinux.cfg', '', d)}"
