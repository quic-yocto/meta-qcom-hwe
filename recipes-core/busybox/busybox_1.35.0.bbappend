FILESEXTRAPATHS:append := "${THISDIR}/${BPN}:"

DEPENDS:append:qcom = "${@bb.utils.contains('DISTRO_FEATURES', 'selinux', ' libselinux (>= 3.2)', '', d)}"

SRC_URI:append:qcom = " ${@bb.utils.contains('DISTRO_FEATURES', 'selinux', ' file://busybox_selinux.cfg', '', d)} \
	"
