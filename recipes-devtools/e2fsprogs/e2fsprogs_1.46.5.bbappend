FILESEXTRAPATHS:append := "${THISDIR}/${BPN}:"

DEPENDS += "libselinux"

SRC_URI += "file://0001-add-selinux-label-to-the-rootfs-files-while-building.patch \
	   "

export LIBSELINUX = "${STAGING_LIBDIR}/libselinux.a ${STAGING_LIBDIR}/libpcre.a"
