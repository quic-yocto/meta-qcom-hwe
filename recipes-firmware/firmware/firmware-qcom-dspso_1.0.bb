DESCRIPTION = "Recipe to install dspso files on rootfs"
LICENSE          = "Qualcomm-Technologies-Inc.-Proprietary"
LIC_FILES_CHKSUM = "file://${QCOM_COMMON_LICENSE_DIR}/${LICENSE};md5=58d50a3d36f27f1a1e6089308a49b403"

COMPATIBLE_MACHINE = "qcm6490"

SRC_URI ="https://${FW_ARTIFACTORY}/${FW_BUILD_ID}/${FW_BIN_PATH}/${DSPSO}.zip;name=${PBT_ARCH}"

SRC_URI[qcm6490.sha256sum] = "8cf953b6bc268cf5be0e93a8a2712208d60268f4996c130bbc3667e5da7ffb35"

include firmware-common.inc

MATCHED_MACHINE = "${@get_matching_machine(d)}"
include firmware-${MATCHED_MACHINE}.inc

DSPSO:qcm6490 = "QCM6490_dspso"
DSPSO:qcs9100 = "QCS9100_dspso"

DSPSO_PATH = "${WORKDIR}/${BIN_PATH}"

do_configure[noexec] = "1"
do_compile[noexec] = "1"
EXCLUDE_FROM_SHLIBS = "1"

python do_install() {

    fw_file = d.getVar("DSPSO")
    fw_path = d.getVar("DSPSO_PATH")

    firmware_install(d, fw_file, fw_path)
}

INHIBIT_PACKAGE_DEBUG_SPLIT = "1"
INHIBIT_PACKAGE_STRIP = "1"
INHIBIT_SYSROOT_STRIP = "1"

PACKAGE_ARCH = "${MACHINE_ARCH}"

PACKAGES += "${PN}-copyright"

FILES:${PN} += "/lib/* /usr/*"
FILES:${PN}-copyright += "/Qualcomm-Technologies-Inc.-Proprietary"

INSANE_SKIP:${PN} += "arch file-rdeps ldflags"
