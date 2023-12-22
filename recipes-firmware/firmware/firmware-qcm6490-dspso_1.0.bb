include firmware-qcm6490.inc

DESCRIPTION = "Recipe to install dspso files on rootfs"

LICENSE          = "Qualcomm-Technologies-Inc.-Proprietary"
LIC_FILES_CHKSUM = "file://${QCOM_COMMON_LICENSE_DIR}/${LICENSE};md5=58d50a3d36f27f1a1e6089308a49b403"

QCM_DSPSO = "QCM6490_dspso"
QCM_DSPSO_PATH = "${WORKDIR}/git/${BUILD_ID}/${BIN_PATH}"

SRC_URI ="git://${CHIPCODE_SRC_URI}.git;branch=${CHIPCODE_SRC_BRANCH};protocol=https"
SRCREV = "${CHIPCODE_SRC_REV}"

python do_install() {

    import os
    import shutil

    srcdir = d.getVar('QCM_DSPSO_PATH')
    dstdir = d.getVar('S')

    def find_file(root_folder, file_name):
        for root, dir, files in os.walk(root_folder):
            if file_name in files:
                return os.path.join(root, file_name)
        return None

    dspso_files = d.getVar('QCM_DSPSO', True)
    filename = f'{dspso_files}.zip'

    zipfile = find_file(srcdir, filename)

    if zipfile:
        if os.path.exists(zipfile):
            shutil.unpack_archive(zipfile, dstdir)

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
