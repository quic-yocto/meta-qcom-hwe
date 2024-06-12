FILESEXTRAPATHS:prepend := "${THISDIR}/files:"

LIC_FILES_CHKSUM = "file://LICENSE;md5=da6bfde9cb5bc5120a51775381f6edf1"

DEPENDS = "libxml2 libusb1"

SRCREV = "cbd46184d33af597664e08aff2b9181ae2f87aa6"
SRC_URI = "git://github.com/linux-msm/${BPN}.git;branch=master;protocol=https \
           file://0001-qdl-add-logs-indicating-flashing-progress.patch"
