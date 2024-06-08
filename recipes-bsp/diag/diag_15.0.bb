inherit qprebuilt pkgconfig systemd

LICENSE          = "Qualcomm-Technologies-Inc.-Proprietary"
LIC_FILES_CHKSUM = "file://${QCOM_COMMON_LICENSE_DIR}${LICENSE};md5=58d50a3d36f27f1a1e6089308a49b403"

DESCRIPTION = "Library and routing applications for diagnostic traffic"

DEPENDS += "glib-2.0 time-genoff"

SRC_URI[qcm6490.sha256sum] = "9bc53966ccbdd4bf3617cfed5b5fc150716a1bb7efa063831f63f85a8373b475"
SRC_URI[qcs9100.sha256sum] = "da0691276164ad735efb6446a6623e69ecfa8323013ca8d4ffc9f7705f3a10f8"

SRC_URI = "https://${PBT_ARTIFACTORY}/${PBT_BUILD_ID}/${PBT_BIN_PATH}/${BPN}_${PV}_${PBT_ARCH}.tar.gz;name=${PBT_ARCH}"

PACKAGE_ARCH = "${MACHINE_ARCH}"

FILES:${PN} += "${systemd_unitdir}/system/"

