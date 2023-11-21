DESCRIPTION = "Recipe to install NHLOS images in DEPLOY_DIR"
LICENSE          = "Qualcomm-Technologies-Inc.-Proprietary"
LIC_FILES_CHKSUM = "file://${QCOM_COMMON_LICENSE_DIR}/${LICENSE};md5=58d50a3d36f27f1a1e6089308a49b403"

QCM_BOOTBINARIES = "QCM6490_bootbinaries"

SRC_URI ="git://qpm-git.qualcomm.com/home2/git/qualcomm/qualcomm-linux-spf-1-0_test_device_public.git;branch=master;protocol=https"
SRCREV = "78937e14c5d782cf68e2f6f791066dc58b4c75ed"

python do_unpack() {
    bb.build.exec_func('base_do_unpack', d)

    firmware = d.getVar('QCM_BOOTBINARIES', True)
    filename = f'{firmware}.zip'

    import os
    import shutil
    
    workdir = d.getVar('WORKDIR', True)

    def find_file(root_folder, file_name):
        for root, dir, files in os.walk(workdir):
            if file_name in files:
                return os.path.join(root, file_name)
        return None

    zipfile = find_file(workdir, filename)
    
    if zipfile:
        if os.path.exists(zipfile):
            shutil.unpack_archive(zipfile, workdir)
}

do_configure[noexec] = "1"
do_compile[noexec] = "1"
do_install[noexec] = "1"

inherit deploy

do_deploy() {
    install -D -m644 ${WORKDIR}/${QCM_BOOTBINARIES}/*.elf ${DEPLOYDIR}
    install -D -m644 ${WORKDIR}/${QCM_BOOTBINARIES}/*.mbn ${DEPLOYDIR}
    install -D -m644 ${WORKDIR}/${QCM_BOOTBINARIES}/*.dat ${DEPLOYDIR}
    install -D -m644 ${WORKDIR}/${QCM_BOOTBINARIES}/*.bin ${DEPLOYDIR}
    for file in ${WORKDIR}/${QCM_BOOTBINARIES}/*.fv; do
        if [ -f "$file" ]; then
            install -D -m644 ${WORKDIR}/${QCM_BOOTBINARIES}/*.fv ${DEPLOYDIR}
        fi
    done
}

addtask deploy before do_build after do_install

PACKAGE_ARCH = "${MACHINE_ARCH}"

FILES:${PN} += "/lib/* "
