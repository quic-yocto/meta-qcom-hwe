inherit qprebuilt

LICENSE          = "Qualcomm-Technologies-Inc.-Proprietary"
LIC_FILES_CHKSUM = "file://${QCOM_COMMON_LICENSE_DIR}${LICENSE};md5=58d50a3d36f27f1a1e6089308a49b403"

DESCRIPTION = "Recipe to install video firmware files on rootfs"

PBT_ARCH = "armv8-2a"

ARMV8_SHA256SUM = "23d5c3ee4a11289e174adf056d44afa1045374ca914f81d821017b446551053f"
SRC_URI[armv8-2a.sha256sum] = "${ARMV8_SHA256SUM}"

SRC_URI = "https://${PBT_ARTIFACTORY}/${PBT_BUILD_ID}/${PBT_BIN_PATH}/${BPN}_${PV}_${PBT_ARCH}.tar.gz;name=${PBT_ARCH}"

FILES:${PN} += "${nonarch_base_libdir}/firmware"


INSANE_SKIP:${PN} = "arch"


INHIBIT_PACKAGE_DEBUG_SPLIT = "1"
