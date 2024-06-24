inherit qprebuilt pkgconfig

LICENSE          = "Qualcomm-Technologies-Inc.-Proprietary"
LIC_FILES_CHKSUM = "file://${QCOM_COMMON_LICENSE_DIR}${LICENSE};md5=58d50a3d36f27f1a1e6089308a49b403"

DESCRIPTION = "Securemsm headers"

SRC_URI[qcm6490.sha256sum] = "6908865671617f8f1eca9edabe85f3965a9f885bea458a4dc19a0a7f7ec54119"
SRC_URI[qcs9100.sha256sum] = "6be2facf6bf71c88c2cdba25f4a12698d04e04c9c6779a0c2a8cf641ad20efd7"

SRC_URI = "https://${PBT_ARTIFACTORY}/${PBT_BUILD_ID}/${PBT_BIN_PATH}/${BPN}_${PV}_${PBT_ARCH}.tar.gz;name=${PBT_ARCH}"

PACKAGE_ARCH = "${MACHINE_ARCH}"

ALLOW_EMPTY:${PN} = "1"
