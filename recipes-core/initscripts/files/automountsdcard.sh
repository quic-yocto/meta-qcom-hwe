#!/bin/sh
MOUNT_DIR="/media/sdcard"
DEVICE="/dev/$2"

mount_sdcard() {
    echo " "
        echo "--------------- SD Card Mount ------------------"
    echo "Insertion Time : $(date)"
    echo "Mounting       : $DEVICE to $MOUNT_DIR"
    if [ -d "$MOUNT_DIR" ]; then
        time mount -t auto "$DEVICE" "$MOUNT_DIR"
        if [ $? -eq 0 ]; then
            echo "Mount          : successful"
            display_sdcard_info
            check_disk_status
        else
            echo "Mount          : failed"
        fi
    else
        echo "Directory      : $MOUNT_DIR not found"
    fi
}

unmount_sdcard() {
    echo " "
    echo "--------------- SD Card Unmount ------------------"
    echo "Removal Time   : $(date)"
    echo "Unmounting     : $DEVICE from $MOUNT_DIR"
    cd /
    if grep -qs "^/dev/$2" /proc/mounts; then
        time umount -lf "$DEVICE"
        if [ $? -eq 0 ]; then
            echo "Unmount        : successful"
        else
            echo "Unmount        : failed"
        fi
    else
        echo "Device         : $DEVICE not mounted"
    fi
    echo " "
}

display_sdcard_info() {
    FORMAT_TYPE=$(blkid -o value -s TYPE "$DEVICE")
    STORAGE_SIZE=$(lsblk -no SIZE "$DEVICE")
    echo "Format         : $FORMAT_TYPE"
    echo "Size           : $STORAGE_SIZE"
}

check_disk_status() {
    fsck -n "$DEVICE" > /dev/null 2>&1
    if [ $? -eq 0 ]; then
        echo "Disk Status    : normal"
    else
        echo "Disk Status    : corrupted"
    fi
}

case "$1" in
    add)
        mount_sdcard
        ;;
    remove)
        unmount_sdcard
        ;;
esac
