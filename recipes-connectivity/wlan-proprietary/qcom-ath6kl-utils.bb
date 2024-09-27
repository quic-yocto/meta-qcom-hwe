inherit qprebuilt pkgconfig

LICENSE          = "Qualcomm-Technologies-Inc.-Proprietary"
LIC_FILES_CHKSUM = "file://${QCOM_COMMON_LICENSE_DIR}${LICENSE};md5=58d50a3d36f27f1a1e6089308a49b403"

DESCRIPTION = "Qualcomm Atheros ath6kl utils."

DEPENDS += "diag libnl glib-2.0"

PV = "1.0"

SRC_URI[qcm6490.sha256sum] = "7a5315c8209aa7949fc8cec98a60a224ff7c05677ddfd70e0f58de73a148c432"
SRC_URI[qcs9100.sha256sum] = "fbe3e3934b53c70ac368bf934474e0bc659bf8ee4784d3ab13f7049b141b894b"
SRC_URI[qcs8300.sha256sum] = "19437b62ff17f9600dfdd41bcb27ceb0ee3b562664f643a5b2393b5dfa64cdb9"

SRC_URI = "https://${PBT_ARTIFACTORY}/${PBT_BUILD_ID}/${PBT_BIN_PATH}/${BPN}_${PV}_${PBT_ARCH}.tar.gz;name=${PBT_ARCH}"
