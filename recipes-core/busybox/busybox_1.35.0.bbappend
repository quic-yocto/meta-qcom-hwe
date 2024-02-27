FILESEXTRAPATHS:append := "${THISDIR}/${BPN}:"

DEPENDS += "${@bb.utils.contains('DISTRO_FEATURES', 'selinux', 'libselinux (>= 3.2)', '', d)}"

SRC_URI += " ${@bb.utils.contains('DISTRO_FEATURES', 'selinux', ' file://busybox_selinux.cfg', '', d)} \
	"
