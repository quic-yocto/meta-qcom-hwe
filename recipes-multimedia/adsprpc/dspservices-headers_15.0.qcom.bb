inherit qprebuilt

LICENSE          = "Qualcomm-Technologies-Inc.-Proprietary"
LIC_FILES_CHKSUM = "file://${QCOM_COMMON_LICENSE_DIR}${LICENSE};md5=58d50a3d36f27f1a1e6089308a49b403"

DESCRIPTION = "dspservices headers."

PBT_ARCH = "aarch64"

SRC_URI[aarch64.sha256sum] = "d7fc145ece1d053452ccc21ebf02ac2966fea1e7ce6b9443fa16140362d6a770"

SRC_URI = "https://${PBT_ARTIFACTORY}/${PBT_BUILD_ID}/${PBT_BIN_PATH}/${BPN}_${PV}_${PBT_ARCH}.tar.gz;name=${PBT_ARCH}"

ALLOW_EMPTY:${PN} = "1"
