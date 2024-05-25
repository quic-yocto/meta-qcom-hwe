include firmware-qcm6490.inc

DESCRIPTION = "Recipe to install firmware files on rootfs"

LICENSE          = "Qualcomm-Technologies-Inc.-Proprietary"
LIC_FILES_CHKSUM = "file://${QCOM_COMMON_LICENSE_DIR}/${LICENSE};md5=58d50a3d36f27f1a1e6089308a49b403"

QCM_FIRMWARE = "QCM6490_fw"
QCM_FIRMWARE_PATH = "${WORKDIR}/git/${FW_BUILD_ID}/${FW_BIN_PATH}"

SRC_URI ="https://${FW_ARTIFACTORY}/${FW_BUILD_ID}/${FW_BIN_PATH}/${QCM_FIRMWARE}.zip"
SRC_URI[sha256sum] = "ab730c467a4e0e4c5e89da980d6e5efd1129c30993aabfcfe6a83415ef6fa7a5"

python do_install() {

    import os
    import shutil

    srcdir = d.getVar('QCM_FIRMWARE_PATH')
    dstdir = d.getVar('WORKDIR')

    firmware = d.getVar('QCM_FIRMWARE')

    # Move content into ${D}
    root = os.path.join(dstdir, firmware)
    for item in os.listdir(root):
        if os.path.isdir(os.path.join(root, item)):
            shutil.copytree(os.path.join(root, item), os.path.join(d.getVar('D'), item))
        elif os.path.isfile(os.path.join(root, item)):
            shutil.copy2(os.path.join(root, item), d.getVar('D'))
}

do_configure[noexec] = "1"
do_compile[noexec] = "1"

INHIBIT_PACKAGE_DEBUG_SPLIT = "1"
INHIBIT_PACKAGE_STRIP = "1"
INHIBIT_SYSROOT_STRIP = "1"

PACKAGE_ARCH = "${MACHINE_ARCH}"

PACKAGES += "${PN}-copyright"

FILES:${PN} += "/lib/* /usr/*"
FILES:${PN}-copyright += "/Qualcomm-Technologies-Inc.-Proprietary"

INSANE_SKIP:${PN} = "arch file-rdeps ldflags"
