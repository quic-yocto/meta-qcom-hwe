inherit qprebuilt pkgconfig systemd

LICENSE          = "Qualcomm-Technologies-Inc.-Proprietary"
LIC_FILES_CHKSUM = "file://${QCOM_COMMON_LICENSE_DIR}${LICENSE};md5=58d50a3d36f27f1a1e6089308a49b403"

DESCRIPTION = "Library and routing applications for diagnostic traffic"

DEPENDS += "glib-2.0 time-genoff"

SRC_URI[qcm6490.sha256sum] = "66d129249efaa157d59294de8a4e47fa578a4a03e3733d48436ab96036c7bf18"
SRC_URI[qcs9100.sha256sum] = "54c2413d6df4a15543aa031cd797c1f0c59126df73d457de54797316857b712f"
SRC_URI[qcs8300.sha256sum] = "dcb9cee359100b98923f062360f72ffd61d1854fda386edbe238580eaa30f811"

SRC_URI = "https://${PBT_ARTIFACTORY}/${PBT_BUILD_ID}/${PBT_BIN_PATH}/${BPN}_${PV}_${PBT_ARCH}.tar.gz;name=${PBT_ARCH}"

FILES:${PN} += "${systemd_unitdir}/system/"

