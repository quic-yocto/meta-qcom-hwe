DESCRIPTION = "Recipe to install partition.xml in DEPLOY_DIR"
LICENSE          = "Qualcomm-Technologies-Inc.-Proprietary"
LIC_FILES_CHKSUM = "file://${QCOM_COMMON_LICENSE_DIR}/${LICENSE};md5=58d50a3d36f27f1a1e6089308a49b403"

COMPATIBLE_MACHINE = "qcm6490"

PROVIDES += "virtual/partconf"

SRC_URI ="https://${FW_ARTIFACTORY}/${FW_BUILD_ID}/${FW_BIN_PATH}/${BOOTBINARIES}.zip;name=${PBT_ARCH}"

SRC_URI[qcm6490.sha256sum] = "74518836d4452a6897e1406a3530c2b039ea847a59c0cfffeeaf629d367f771d"

include firmware-common.inc

MATCHED_MACHINE = "${@get_matching_machine(d)}"
include firmware-${MATCHED_MACHINE}.inc

BOOTBINARIES:qcm6490 = "QCM6490_bootbinaries"
BOOTBINARIES:qcs9100 = "QCS9100_bootbinaries"

BOOTBINARIES_PATH = "${WORKDIR}/git/${BUILD_ID}/${BIN_PATH}"

do_configure[noexec] = "1"
do_compile[noexec] = "1"

python do_install() {

    fw_file = d.getVar("BOOTBINARIES")
    fw_path = d.getVar("BOOTBINARIES_PATH")

    firmware_install(d, fw_file, fw_path)

    # Remove all files except partition.conf
    for item in os.listdir(d.getVar('D')):
        if item == 'partition.xml':
            continue
        else:
            os.remove(os.path.join(d.getVar('D'), item))

}

inherit deploy

do_deploy() {
    find "${D}" -name 'partition.xml' -exec install -m 0644 {} ${DEPLOYDIR} \;
}
addtask deploy before do_build after do_install

PACKAGE_ARCH = "${MACHINE_ARCH}"

PACKAGES += "${PN}-copyright"

FILES:${PN} += "/partition.xml"
FILES:${PN}-copyright += "/Qualcomm-Technologies-Inc.-Proprietary"

INSANE_SKIP:${PN} = "arch"
