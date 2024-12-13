DESCRIPTION = "QCOM NHLOS Firmware for Qualcomm Robotics RB3Gen2 platform"
LICENSE = "Proprietary"
LIC_FILES_CHKSUM = "file://${UNPACKDIR}/${BOOTBINARIES}/Qualcomm-Technologies-Inc.-Proprietary;md5=58d50a3d36f27f1a1e6089308a49b403"

COMPATIBLE_MACHINE = "qcm6490"

FW_ARTIFACTORY = "softwarecenter.qualcomm.com/download/software/chip/qualcomm_linux-spf-1-0/qualcomm-linux-spf-1-0_test_device_integrationandtest_publictest"
FW_BUILD_ID = "r1.0_${PV}/qcm6490-le-1-0"
FW_BIN_PATH = "common/build/ufs/bin"
BOOTBINARIES = "QCM6490_bootbinaries"

SRC_URI = "https://${FW_ARTIFACTORY}/${FW_BUILD_ID}/${FW_BIN_PATH}/${BOOTBINARIES}.zip;downloadfilename=${BOOTBINARIES}_r1.0_${PV}.zip"
SRC_URI[sha256sum] = "5e597229af9103cfea5b398c7e83a05dd078a18af010a40f1b9adf92967d4c1e"

include firmware-qcom-boot-common.inc
