include firmware-qcm6490.inc

DESCRIPTION = "Recipe to install dspso files on rootfs"

LICENSE          = "Qualcomm-Technologies-Inc.-Proprietary"
LIC_FILES_CHKSUM = "file://${QCOM_COMMON_LICENSE_DIR}/${LICENSE};md5=58d50a3d36f27f1a1e6089308a49b403"

QCM_DSPSO = "QCM6490_dspso"
QCM_DSPSO_PATH = "${WORKDIR}/git/${BUILD_ID}/${BIN_PATH}"

SRC_URI ="https://${NHLOS_ARTIFACTORY}/${BUILD_ID}/${BUILD_TYPE}/${BIN_PATH}/${QCM_DSPSO}.zip"
SRC_URI[sha256sum] = "354db0aafcabe93785b688007f54d165a5894de5a39174e5370e0847e3dad74f"

python do_install() {

    import os
    import shutil

    srcdir = d.getVar('QCM_DSPSO_PATH')
    dstdir = d.getVar('WORKDIR')
    firmware = d.getVar('QCM_DSPSO')
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

INSANE_SKIP:${PN} = "file-rdeps"
INSANE_SKIP:${PN} += "ldflags"
INSANE_SKIP:${PN} += "arch"

FILES:${PN} += "/lib/* /usr/*"
FILES:${PN}-copyright += "/Qualcomm-Technologies-Inc.-Proprietary"
