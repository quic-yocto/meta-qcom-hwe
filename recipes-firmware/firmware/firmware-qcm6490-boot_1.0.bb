include firmware-qcm6490.inc

DESCRIPTION = "Recipe to install NHLOS images in DEPLOY_DIR"
LICENSE          = "Qualcomm-Technologies-Inc.-Proprietary"
LIC_FILES_CHKSUM = "file://${QCOM_COMMON_LICENSE_DIR}/${LICENSE};md5=58d50a3d36f27f1a1e6089308a49b403"

QCM_BOOTBINARIES = "QCM6490_bootbinaries"
QCM_BOOTBINARIES_PATH = "${WORKDIR}/git/${FW_BUILD_ID}/${FW_BIN_PATH}"

SRC_URI ="https://${FW_ARTIFACTORY}/${FW_BUILD_ID}/${FW_BIN_PATH}/${QCM_BOOTBINARIES}.zip"
SRC_URI[sha256sum] = "ff61365fb371a90cfea74ca24074d071c8d137f344a288938f27c573fc542130"

PROVIDES += "virtual/bootbins"

python do_install() {

    import os
    import shutil

    srcdir = d.getVar('QCM_BOOTBINARIES_PATH')
    dstdir = d.getVar('WORKDIR')

    firmware = d.getVar('QCM_BOOTBINARIES')

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

inherit deploy

do_deploy() {
    install -D -m644 ${D}/*.elf ${DEPLOYDIR}
    install -D -m644 ${D}/*.mbn ${DEPLOYDIR}
    install -D -m644 ${D}/*.bin ${DEPLOYDIR}
}

addtask deploy before do_build after do_install

PACKAGE_ARCH = "${MACHINE_ARCH}"

PACKAGES += "${PN}-copyright ${PN}-partconf"

FILES:${PN} += "/*.elf /*.mbn /*bin"
FILES:${PN}-copyright += "/Qualcomm-Technologies-Inc.-Proprietary"
FILES:${PN}-partconf += "/partition.xml"

INSANE_SKIP:${PN} = "arch"
