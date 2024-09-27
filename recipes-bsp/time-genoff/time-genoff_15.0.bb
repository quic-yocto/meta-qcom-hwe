inherit qprebuilt pkgconfig

LICENSE          = "Qualcomm-Technologies-Inc.-Proprietary"
LIC_FILES_CHKSUM = "file://${QCOM_COMMON_LICENSE_DIR}${LICENSE};md5=58d50a3d36f27f1a1e6089308a49b403"

DESCRIPTION = "Time Genoff Library"

SRC_URI[qcm6490.sha256sum] = "62c789dbe22af2c227959d30027506f85f9669aaf4c646cd2138da80e670796b"
SRC_URI[qcs9100.sha256sum] = "98d52dc9126fc63c87dd4f999f4b4a7284ae4ffb8c2cf55f50fd953fb4ac235f"
SRC_URI[qcs8300.sha256sum] = "4fac54d1e5bb6379c3443db6d0b572fc80c8dd5f5ca279b58a403aa0a8f6942e"

SRC_URI = "https://${PBT_ARTIFACTORY}/${PBT_BUILD_ID}/${PBT_BIN_PATH}/${BPN}_${PV}_${PBT_ARCH}.tar.gz;name=${PBT_ARCH}"
