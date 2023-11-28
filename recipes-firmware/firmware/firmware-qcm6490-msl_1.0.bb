DESCRIPTION = "Recipe to install firmware files on rootfs"

LICENSE          = "Qualcomm-Technologies-Inc.-Proprietary"
LIC_FILES_CHKSUM = "file://${QCM_FIRMWARE}/license.qcom.txt;md5=41ac068aa1eb11d6e04b08f4dca0f655"

QCM_FIRMWARE = "QCM6490_MSL"

S = "${WORKDIR}"

SRC_URI =""

do_fetch() {
    wget -nH -O ${QCM_FIRMWARE}.zip --no-check-certificate https://artifacts.codelinaro.org/artifactory/clo-386-k2c-yocto/r1.0.00001.12/${QCM_FIRMWARE}.zip
    cp ${QCM_FIRMWARE}.zip ${WORKDIR}
}

python do_unpack() {
    bb.build.exec_func('base_do_unpack', d)

    firmware = d.getVar('QCM_FIRMWARE', True)
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

INHIBIT_PACKAGE_DEBUG_SPLIT = "1"
INHIBIT_PACKAGE_STRIP = "1"
INHIBIT_SYSROOT_STRIP = "1"

do_install() {
    mkdir -p ${D}/lib/firmware
    cp -r ${WORKDIR}/${QCM_FIRMWARE}/lib/firmware/* ${D}/lib/firmware/
    cp -r ${WORKDIR}/${QCM_FIRMWARE}/license.qcom.txt ${D}/lib/firmware/
}

PACKAGE_ARCH = "${MACHINE_ARCH}"

FILES:${PN} += "/lib/* /usr/*"

INSANE_SKIP:${PN} = "arch file-rdeps ldflags"
