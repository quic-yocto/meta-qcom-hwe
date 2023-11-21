# Copyright (c) 2023 Qualcomm Innovation Center, Inc. All rights reserved.
# SPDX-License-Identifier: BSD-3-Clause-Clear

inherit uki

DEPENDS:append = " \
        dosfstools-native \
        mtools-native \
        "

BOOTIMG_VOLUME_ID   ?= "BOOT"
BOOTIMG_EXTRA_SPACE ?= "512"

MKFSVFAT_EXTRAOPTS ?= "-S 512"

ESPIMG_DEPLOYDIR = "${WORKDIR}/espimg-${PN}"
EFIDIR = "/EFI/BOOT"
EFIIMGDIR = "${B}/efi_img"
EFIIMG = "${ESPIMG_DEPLOYDIR}/efi.bin"
EFI_BOOT_IMAGE = "boot${EFI_ARCH}.efi"

build_fat_img() {
    FATSOURCEDIR=$1
    FATIMG=$2

    # Calculate the size required for the final image including the
    # data and filesystem overhead.
    # Sectors: 512 bytes
    # Blocks: 1024 bytes

    # Determine the sector count just for the data
    SECTORS=$(expr $(du --apparent-size -ks ${FATSOURCEDIR} | cut -f 1) \* 2)

    # Account for the filesystem overhead.
    # 32 bytes per dir entry
    DIR_BYTES=$(expr $(find ${FATSOURCEDIR} | tail -n +2 | wc -l) \* 32)
    # 32 bytes for every end-of-directory dir entry
    DIR_BYTES=$(expr $DIR_BYTES + $(expr $(find ${FATSOURCEDIR} -type d | tail -n +2 | wc -l) \* 32))
    # 4 bytes per FAT entry per sector of data
    FAT_BYTES=$(expr $SECTORS \* 4)
    # 4 bytes per FAT entry per end-of-cluster list
    FAT_BYTES=$(expr $FAT_BYTES + $(expr $(find ${FATSOURCEDIR} -type d | tail -n +2 | wc -l) \* 4))

    # Use a ceiling function to determine FS overhead in sectors
    DIR_SECTORS=$(expr $(expr $DIR_BYTES + 511) / 512)
    # There are two FATs on the image
    FAT_SECTORS=$(expr $(expr $(expr $FAT_BYTES + 511) / 512) \* 2)
    SECTORS=$(expr $SECTORS + $(expr $DIR_SECTORS + $FAT_SECTORS))

    # Determine the final size in blocks accounting for some padding
    BLOCKS=$(expr $(expr $SECTORS / 2) + ${BOOTIMG_EXTRA_SPACE})

    # mkfs.vfat will sometimes use FAT16 when it is not appropriate,
    # resulting in a boot failure. Use FAT32 for images larger
    # than 512MB, otherwise let mkfs.vfat decide.
    if [ $(expr $BLOCKS / 1024) -gt 512 ]; then
        FATSIZE="-F 32"
    fi

    # Delete any previous image.
    if [ -e ${FATIMG} ]; then
        rm ${FATIMG}
    fi

    mkfs.vfat ${FATSIZE} -n ${BOOTIMG_VOLUME_ID} ${MKFSVFAT_EXTRAOPTS} -C ${FATIMG} ${BLOCKS}

    # Copy FATSOURCEDIR recursively into the image file directly
    ${STAGING_BINDIR_NATIVE}/mcopy -i ${FATIMG} -s ${FATSOURCEDIR}/* ::/
}

build_esp() {
    mkdir -p ${EFIIMGDIR}

    #Kernel uki efi
    mkdir -p ${EFIIMGDIR}/EFI/Linux
    cp ${DEPLOY_DIR_IMAGE}/${UKI_FILENAME} ${EFIIMGDIR}/EFI/Linux/

    #loader.conf
    mkdir -p ${EFIIMGDIR}/loader
    touch ${EFIIMGDIR}/loader/loader.conf

    #systemd-boot efi
    mkdir -p ${EFIIMGDIR}${EFIDIR}
    cp ${DEPLOY_DIR_IMAGE}/systemd-boot${EFI_ARCH}.efi ${EFIIMGDIR}${EFIDIR}/${EFI_BOOT_IMAGE}

    # Uefi dtb
    cp ${DEPLOY_DIR_IMAGE}/${UEFI_DTB} ${EFIIMGDIR}

    # Create a vfat image
    build_fat_img ${EFIIMGDIR} ${EFIIMG}
}

do_espimg[depends] += " \
    dosfstools-native:do_populate_sysroot \
    mtools-native:do_populate_sysroot \
    systemd-boot:do_deploy \
    virtual/kernel:do_deploy \
    ${PN}:do_uki \
   "
python do_espimg() {
    bb.build.exec_func('build_esp', d)
}
addtask espimg before do_image_complete after do_rootfs

# Setup sstate
SSTATETASKS += "do_espimg"
do_espimg[sstate-inputdirs] = "${ESPIMG_DEPLOYDIR}"
do_espimg[sstate-outputdirs] = "${DEPLOY_DIR_IMAGE}"

python do_espimg_setscene () {
    sstate_setscene(d)
}
addtask do_espimg_setscene
do_espimg[dirs] = "${ESPIMG_DEPLOYDIR} ${B}"
do_espimg[cleandirs] = "${ESPIMG_DEPLOYDIR}"
do_espimg[stamp-extra-info] = "${MACHINE_ARCH}"
