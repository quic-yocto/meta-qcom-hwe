DESCRIPTION = "Recipe to install dspso files on rootfs"
LICENSE          = "Qualcomm-Technologies-Inc.-Proprietary"
LIC_FILES_CHKSUM = "file://${QCOM_COMMON_LICENSE_DIR}/${LICENSE};md5=58d50a3d36f27f1a1e6089308a49b403"

COMPATIBLE_MACHINE = "qcm6490|qcs9100|qcs8300|qcs615"

SRC_URI ="https://${FW_ARTIFACTORY}/${FW_BUILD_ID}/${FW_BIN_PATH}/${DSPSO}.zip;name=${PBT_ARCH}"

SRC_URI[qcm6490.sha256sum] = "d9dbea356035292b12434ec6bc44a2050edb98de2fd2048f5f62bbcb535ab885"
SRC_URI[qcs9100.sha256sum] = "4ea96522f74a1922b0d85b4b397f7b38791bd6479a8cd1f93cd4be46cdb87d75"
SRC_URI[qcs8300.sha256sum] = "c456a6a7751be8c1ab049dd162332f3ca42410dc274bb4d62fcff3e09cdf7f5a"

include firmware-common.inc

MATCHED_MACHINE = "${@get_matching_machine(d)}"
include firmware-${MATCHED_MACHINE}.inc

DSPSO:qcm6490 = "QCM6490_dspso"
DSPSO:qcs9100 = "QCS9100_dspso"
DSPSO:qcs8300 = "QCS8300_dspso"
DSPSO:qcs615  = "QCS615_dspso"

DSPSO_PATH = "${WORKDIR}/git/${BUILD_ID}/${BIN_PATH}"

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

PACKAGE_ARCH = "${SOC_ARCH}"

PACKAGES += "${PN}-copyright"

FILES:${PN} += "/lib/* /usr/*"
FILES:${PN}-copyright += "/Qualcomm-Technologies-Inc.-Proprietary"

INSANE_SKIP:${PN} = "file-rdeps"
INSANE_SKIP:${PN} += "ldflags"
INSANE_SKIP:${PN} += "arch"
