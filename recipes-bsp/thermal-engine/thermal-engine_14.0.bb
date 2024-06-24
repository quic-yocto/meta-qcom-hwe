inherit qprebuilt pkgconfig systemd

LICENSE          = "Qualcomm-Technologies-Inc.-Proprietary"
LIC_FILES_CHKSUM = "file://${QCOM_COMMON_LICENSE_DIR}${LICENSE};md5=58d50a3d36f27f1a1e6089308a49b403"

DESCRIPTION = "Thermal Engine"

DEPENDS += "qmi-framework glib-2.0 libnl linux-kernel-qcom-headers"

SRC_URI[qcm6490.sha256sum] = "c2f1c926c39274a4b907f679a7d72dd11a2e7878ac8938f299e236f96b1ca7bd"
SRC_URI[qcs9100.sha256sum] = "4c5f58fc4d76de379be50da53e7b3f35cdabb66fb91dc7ef9c5102ec3657a4d6"

SRC_URI = "https://${PBT_ARTIFACTORY}/${PBT_BUILD_ID}/${PBT_BIN_PATH}/${BPN}_${PV}_${PBT_ARCH}.tar.gz;name=${PBT_ARCH}"

PACKAGE_ARCH = "${MACHINE_ARCH}"
