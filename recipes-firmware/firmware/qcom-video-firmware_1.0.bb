inherit qprebuilt

LICENSE          = "Qualcomm-Technologies-Inc.-Proprietary"
LIC_FILES_CHKSUM = "file://${QCOM_COMMON_LICENSE_DIR}${LICENSE};md5=58d50a3d36f27f1a1e6089308a49b403"

DESCRIPTION = "Recipe to install video firmware files on rootfs"

PBT_ARCH = "armv8-2a"

SRC_URI[armv8-2a.sha256sum] = "b371bb0a2ecd6fc4fb0b3891e2c7cae14fcdcb562be3c94238b57038ce0d9d20"

SRC_URI = "https://${PBT_ARTIFACTORY}/${PBT_BUILD_ID}/${PBT_BIN_PATH}/${BPN}_${PV}_${PBT_ARCH}.tar.gz;name=${PBT_ARCH}"

FILES:${PN} += "${nonarch_base_libdir}/firmware"


INSANE_SKIP:${PN} = "arch"


INHIBIT_PACKAGE_DEBUG_SPLIT = "1"
