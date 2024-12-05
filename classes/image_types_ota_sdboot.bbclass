# Support initial customized target via GARAGE_CUSTOMIZE_TARGET
# This is set by our CI scripts and allows the initial target to populated by
# the build process so it can be incorporated at the first aktualizr-lite run
IMAGE_CMD:ota:append () {
	# systemd-boot support
	if [ "${EFI_PROVIDER}" = "systemd-boot" ]; then
		if [ "${OSTREE_LOADER_LINK}" != "0" ]; then
			bbfatal "Systemd-boot requires OSTREE_LOADER_LINK to be set to '0'"
		fi
		if [ "${OSTREE_SPLIT_BOOT}" != "1" ]; then
			bbfatal "Systemd-boot requires OSTREE_SPLIT_BOOT to be set to '1'"
		fi
		if [ "${OSTREE_BOOTLOADER}" != "none" ]; then
			bbfatal "Systemd-boot requires OSTREE_BOOTLOADER to be set to 'none'"
		fi
		# As upstream doesn't yet support systemd-boot, we have to undo none and change as needed
		ostree config --repo=${OTA_SYSROOT}/ostree/repo unset sysroot.bootloader
		touch ${OTA_SYSROOT}/boot/loader/loader.conf
		# Remove boot symlink as partition is vfat/ESP
		rm -f ${OTA_SYSROOT}/boot/boot
		# Install systemd-boot EFI in ota-boot to allow consumption out of wic
		mkdir -p ${OTA_SYSROOT}/boot/EFI/BOOT
		cp ${DEPLOY_DIR_IMAGE}/systemd-boot${EFI_ARCH}.efi ${OTA_SYSROOT}/boot/EFI/BOOT/boot${EFI_ARCH}.efi
		rm -rf ${IMAGE_ROOTFS}/boot
	fi

	# Ostree /boot/loader as link (default) or as directory
	if [ "${OSTREE_LOADER_LINK}" = "0" ]; then
		if [ -h ${OTA_SYSROOT}/boot/loader ]; then
			loader=`readlink ${OTA_SYSROOT}/boot/loader`
			rm -f ${OTA_SYSROOT}/boot/loader
			mv ${OTA_SYSROOT}/boot/${loader} ${OTA_SYSROOT}/boot/loader
			echo -n ${loader} > ${OTA_SYSROOT}/boot/loader/ostree_bootversion
		else
			mkdir -p ${OTA_SYSROOT}/boot/loader
			echo -n "loader.0" > ${OTA_SYSROOT}/boot/loader/ostree_bootversion
		fi
	fi

	# Split content from /boot into a separated folder so it can be consumed by WKS separately
	if [ "${OSTREE_SPLIT_BOOT}" = "1" ]; then
		rm -rf ${OTA_BOOT}
		mv ${OTA_SYSROOT}/boot ${OTA_BOOT}
		mkdir -p ${OTA_SYSROOT}/boot
	fi

}

OTA_BOOT = "${WORKDIR}/ota-boot"
do_image_ota[dirs] += "${OTA_BOOT}"
do_image_ota[cleandirs] += "${OTA_BOOT}"
do_image_ota[depends] += "systemd-boot:do_deploy"

# Adapted from oe_mkext234fs in image_types.bbclass
oe_mkotaespfs() {
	fstype="$1"
	extra_imagecmd=""

	if [ $# -gt 1 ]; then
		shift
		extra_imagecmd=$@
	fi

	# Create a sparse image block. ESP partition must be 64K blocks.
	bbdebug 1 Executing "dd if=/dev/zero of=${IMGDEPLOYDIR}/esp-qcom-image-${MACHINE}.vfat seek=524288 count=0 bs=1024"
	dd if=/dev/zero of=${IMGDEPLOYDIR}/esp-qcom-image-${MACHINE}.vfat seek=524288 count=0 bs=1024
	bbdebug 1 "Actual ESP size: `du -s ${OTA_BOOT}`"
	bbdebug 1 "Actual Partition size: `stat -c '%s' ${IMGDEPLOYDIR}/esp-qcom-image-${MACHINE}.vfat`"
	bbdebug 1 "${IMGDEPLOYDIR}/esp-qcom-image-${MACHINE}.vfat"
	bbdebug 1 Executing "mkfs.vfat -F 32 -I $extra_imagecmd ${IMGDEPLOYDIR}/esp-qcom-image-${MACHINE}.vfat "
	mkfs.vfat -F 32 -I $extra_imagecmd ${IMGDEPLOYDIR}/esp-qcom-image-${MACHINE}.vfat
	mcopy -i ${IMGDEPLOYDIR}/esp-qcom-image-${MACHINE}.vfat -s ${OTA_BOOT}/* ::/
	# Error codes 0-3 indicate successfull operation of fsck (no errors or errors corrected)
	fsck.vfat -pvfV ${IMGDEPLOYDIR}/esp-qcom-image-${MACHINE}.vfat
}
do_image_ota_esp[depends] += "dosfstools-native:do_populate_sysroot mtools-native:do_populate_sysroot"
IMAGE_FSTYPES_OTA ?= ""
IMAGE_FSTYPES_OTA:sota = "${@bb.utils.contains('EFI_PROVIDER', 'systemd-boot', 'ota-esp', '', d)}"
IMAGE_FSTYPES += "${IMAGE_FSTYPES_OTA}"
IMAGE_TYPEDEP:ota-esp = "ota"
IMAGE_TYPES += "ota-esp"
EXTRA_IMAGECMD:ota-esp ?= "-s 1 -S 4096"
IMAGE_CMD:ota-esp = "oe_mkotaespfs ota-esp ${EXTRA_IMAGECMD}"
SSTATE_ALLOW_OVERLAP_FILES += " ${DEPLOY_DIR_IMAGE}/esp-qcom-image-${MACHINE}.vfat "
