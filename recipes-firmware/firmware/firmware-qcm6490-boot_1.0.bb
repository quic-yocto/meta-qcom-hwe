DESCRIPTION = "Recipe to install NHLOS images in DEPLOY_DIR"
LICENSE          = "Qualcomm-Technologies-Inc.-Proprietary"
LIC_FILES_CHKSUM = "file://${QCM_BOOTBINARIES}/license.qcom.txt;md5=41ac068aa1eb11d6e04b08f4dca0f655"

QCM_BOOTBINARIES = "QCM6490_bootbinaries"

S = "${WORKDIR}"

SRC_URI =""

do_fetch() {
    wget -nH -O ${QCM_BOOTBINARIES}.zip --no-check-certificate https://artifacts.codelinaro.org/artifactory/clo-386-k2c-yocto/r1.0.00001.12/${QCM_BOOTBINARIES}.zip
    cp ${QCM_BOOTBINARIES}.zip ${WORKDIR}
}

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
    install -D -m644 ${WORKDIR}/${QCM_BOOTBINARIES}/*.bin ${DEPLOYDIR}
}

addtask deploy before do_build after do_install

PACKAGE_ARCH = "${MACHINE_ARCH}"

FILES:${PN} += "/lib/* "
