inherit qprebuilt pkgconfig systemd

LICENSE          = "Qualcomm-Technologies-Inc.-Proprietary"
LIC_FILES_CHKSUM = "file://${QCOM_COMMON_LICENSE_DIR}${LICENSE};md5=58d50a3d36f27f1a1e6089308a49b403"

SUMMARY = "FastRPC user space libraries and daemons needed to offload to DSPs"

DEPENDS += "dspservices-headers qcom-libdmabufheap"

DEFAULT_PREFERENCE = "-1"

PBT_ARCH = "${MACHINE_ARCH}"

QCM6490_IDP_SHA256SUM = "13df2cdf9afec576dd1411391d25036679e26e10bf673c69f77b44324cdb4739"
QCS6490_RB3GEN2_CORE_KIT_SHA256SUM = "7cbc787b4c08aacbf0be5f846839a4757d9bba21265e7176aef300cc118134f0"
QCS6490_RB3GEN2_VISION_KIT_SHA256SUM = "bd2c70f481efa903c4c0549cd8dd758a88643abf895265499169cd24dab625e4"
QCS8300_RIDE_SX_SHA256SUM = "c0fcdb7149d52904b0a6a8bf774cdaa50cac0ce2db3e6de1bdf89f099619953e"
QCS9100_RIDE_SX_SHA256SUM = "940fcb0127fbb7a7ec8c14c37e9df387d88aa922341d1f84b7f59187902aafe9"

SRC_URI[qcm6490_idp.sha256sum] = "${QCM6490_IDP_SHA256SUM}"
SRC_URI[qcs6490_rb3gen2_core_kit.sha256sum] = "${QCS6490_RB3GEN2_CORE_KIT_SHA256SUM}"
SRC_URI[qcs6490_rb3gen2_vision_kit.sha256sum] = "${QCS6490_RB3GEN2_VISION_KIT_SHA256SUM}"
SRC_URI[qcs8300_ride_sx.sha256sum] = "${QCS8300_RIDE_SX_SHA256SUM}"
SRC_URI[qcs9100_ride_sx.sha256sum] = "${QCS9100_RIDE_SX_SHA256SUM}"

SRC_URI = "https://${PBT_ARTIFACTORY}/${PBT_BUILD_ID}/${PBT_BIN_PATH}/${BPN}_${PV}_${PBT_ARCH}.tar.gz;name=${PBT_ARCH}"

# Install systemd unit file at right location
relocate_systemd_unit_files () {
    if [ -d "${D}/lib/systemd" ]; then
        install -d ${D}/usr/lib/
        mv ${D}/lib/systemd  ${D}/usr/lib/systemd
    fi
}
do_install[postfuncs] += "relocate_systemd_unit_files"

FILES:${PN} += "${libdir}/*.so ${libdir}/pkgconfig/ ${systemd_unitdir}/system/* ${sysconfdir}/* ${bindir}/*"
FILES:${PN}-dev = "${libdir}/*.la ${includedir}"

PACKAGE_ARCH    ?= "${MACHINE_ARCH}"

INSANE_SKIP:${PN} += "already-stripped"
INSANE_SKIP:${PN} += "installed-vs-shipped"
INSANE_SKIP:${PN} += "dev-so"

SYSTEMD_SERVICE:${PN} = " adsprpcd.service cdsprpcd.service"
SYSTEMD_SERVICE:${PN}:append:qcs9100 = " cdsp1rpcd.service"
