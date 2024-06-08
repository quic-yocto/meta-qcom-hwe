inherit qprebuilt

LICENSE          = "Qualcomm-Technologies-Inc.-Proprietary"
LIC_FILES_CHKSUM = "file://${QCOM_COMMON_LICENSE_DIR}${LICENSE};md5=58d50a3d36f27f1a1e6089308a49b403"

DESCRIPTION = "Recipe to install video firmware files on rootfs"

SRC_URI[sha256sum] = "b7640d56190d779dd00bfc2f61eec639f1dcf1467829d0737fb46e05f4d069a7"

SRC_URI = "https://${PBT_ARTIFACTORY}/${PBT_BUILD_ID}/${PBT_BIN_PATH}/${BPN}_${PV}_${PBT_ARCH}.tar.gz;name=${PBT_ARCH}"

PACKAGE_ARCH = "${MACHINE_ARCH}"

FILES:${PN} += "/lib/firmware"

INSANE_SKIP:${PN} = "arch"

INHIBIT_PACKAGE_DEBUG_SPLIT = "1"
