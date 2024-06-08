inherit qprebuilt pkgconfig systemd

LICENSE          = "Qualcomm-Technologies-Inc.-Proprietary"
LIC_FILES_CHKSUM = "file://${QCOM_COMMON_LICENSE_DIR}${LICENSE};md5=58d50a3d36f27f1a1e6089308a49b403"

DESCRIPTION = "adsprpc daemon."

DEPENDS += "dspservices-headers libdmabufheap"

SRC_URI[qcm6490.sha256sum] = "1e86f54568a7af79725c22b1bba92a0364f8275bb0ae8e8213f3597d0cdb30a7"
SRC_URI[qcs9100.sha256sum] = "0f12ce0bab8b90098db5ec0529720c47ad549ed37f3e23a77c8c808f46980a67"

SRC_URI = "https://${PBT_ARTIFACTORY}/${PBT_BUILD_ID}/${PBT_BIN_PATH}/${BPN}_${PV}_${PBT_ARCH}.tar.gz;name=${PBT_ARCH}"

PACKAGE_ARCH = "${MACHINE_ARCH}"

FILES:${PN} += "${libdir}/*.so ${libdir}/pkgconfig/ ${systemd_unitdir}/system/* ${sysconfdir}/* ${bindir}/*"
FILES:${PN}-dev = "${libdir}/*.la ${includedir}"


INSANE_SKIP:${PN} += "already-stripped"
INSANE_SKIP:${PN} += "installed-vs-shipped"

SYSTEMD_SERVICE:${PN} = " adsprpcd.service cdsprpcd.service"
