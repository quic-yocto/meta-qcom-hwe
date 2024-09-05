# Copyright (c) 2024 Qualcomm Innovation Center, Inc. All rights reserved.
# SPDX-License-Identifier: BSD-3-Clause-Clear

inherit image_types

# Note that vfat can't handle all types of files that a real linux file system
# can (e.g. device files, symlinks, etc.) and therefore it not suitable for all
# use cases

oe_mkvfatfs () {
    mkfs.vfat $@ -s 16 -S 4096 -C ${IMGDEPLOYDIR}/${IMAGE_NAME}${IMAGE_NAME_SUFFIX}.vfat ${ROOTFS_SIZE}
    mcopy -i "${IMGDEPLOYDIR}/${IMAGE_NAME}${IMAGE_NAME_SUFFIX}.vfat" -vsmpQ ${IMAGE_ROOTFS}/* ::/
}

IMAGE_CMD:vfat = "oe_mkvfatfs ${EXTRA_IMAGECMD}"

# If a specific FAT size is needed, set it here (e.g. "-F 32"/"-F 16"/"-F 12")
# otherwise mkfs.vfat will automatically pick one.
EXTRA_IMAGECMD:vfat ?= ""

do_image_vfat[depends] += "dosfstools-native:do_populate_sysroot mtools-native:do_populate_sysroot"

IMAGE_TYPES:append = " \
    vfat \
"
