inherit qprebuilt pkgconfig systemd

LICENSE          = "Qualcomm-Technologies-Inc.-Proprietary"
LIC_FILES_CHKSUM = "file://${QCOM_COMMON_LICENSE_DIR}${LICENSE};md5=58d50a3d36f27f1a1e6089308a49b403"

DESCRIPTION = "adsprpc daemon."

DEPENDS += "dspservices-headers libdmabufheap"

PBT_ARCH = "${MACHINE_ARCH}"
SRC_URI[qcs6490_rb3gen2_core_kit.sha256sum] = "156c79f1de7f83edf9ce3c5a7fd92e13e329a7437764046733d64ea4a4032bb7"
SRC_URI[qcs6490_rb3gen2_vision_kit.sha256sum] = "1bc76becfdbfc143073555cd2d05d3833a787e889eb7675251040262fd7ef6b7"
SRC_URI[qcs8300_ride_sx.sha256sum] = "02f95473d196947424e548acf11b00bcca75a5df5bb4b434b4bb13de1ad4ce30"
SRC_URI[qcs9100_ride_sx.sha256sum] = "08c1035367a1987fac3904b33dcc74cfee3217177b72bdd7c5bd7874c282b2d3"

SRC_URI = "https://${PBT_ARTIFACTORY}/${PBT_BUILD_ID}/${PBT_BIN_PATH}/${BPN}_${PV}_${PBT_ARCH}.tar.gz;name=${PBT_ARCH}"

FILES:${PN} += "${libdir}/*.so ${libdir}/pkgconfig/ ${systemd_unitdir}/system/* ${sysconfdir}/* ${bindir}/*"
FILES:${PN}-dev = "${libdir}/*.la ${includedir}"


INSANE_SKIP:${PN} += "already-stripped"
INSANE_SKIP:${PN} += "installed-vs-shipped"

SYSTEMD_SERVICE:${PN} = " adsprpcd.service cdsprpcd.service"
SYSTEMD_SERVICE:${PN}:append:qcs9100 = " cdsp1rpcd.service"
