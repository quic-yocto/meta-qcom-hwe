inherit qprebuilt pkgconfig systemd

LICENSE          = "Qualcomm-Technologies-Inc.-Proprietary"
LIC_FILES_CHKSUM = "file://${QCOM_COMMON_LICENSE_DIR}${LICENSE};md5=58d50a3d36f27f1a1e6089308a49b403"

SUMMARY = "FastRPC user space libraries and daemons needed to offload to DSPs"

DEPENDS += "dspservices-headers qcom-libdmabufheap"

DEFAULT_PREFERENCE = "-1"

PBT_ARCH = "${MACHINE_ARCH}"

QCM6490_IDP_SHA256SUM = "fcd9bc826a2b3f83e72183878801e2a15a0135d3594acd4243ddb8b2569ffca7"
QCS6490_RB3GEN2_CORE_KIT_SHA256SUM = "a346ffe4a88948cb27728607a83b396737f06af6be6507784c4165403f28d507"
QCS6490_RB3GEN2_INDUSTRIAL_KIT_SHA256SUM = "cc48ee9e264e34eb6c3ebb1a2c654fd7c6a9c5a4c74edd388153e41a46329d12"
QCS6490_RB3GEN2_VISION_KIT_SHA256SUM = "45540968d1597d30da52b74771288ea7dd15f8ee1f7d71d926614134f30d14e2"
QCS8300_RIDE_SX_SHA256SUM = "6778c1589b4668b4957b998f98c4996cb0ec23aff33c42f53b75528b6385a6ec"
QCS9100_RIDE_SX_SHA256SUM = "f7ef544a39174bd5f73b178408349514e59323d203c685101e813b344e46e9a6"
QCS9075_RIDE_SX_SHA256SUM = "b5ad3a66f4a973cf346efe734b736674f204bfab66d348ca364f9055841e3a06"
QCS9_RB8_CORE_KIT_SHA256SUM = "22b7a2bd3c3839eb9f3e0c8d7ed0984c6d2cc4e626fa67b48901bf6d3abf0616"

SRC_URI[qcm6490_idp.sha256sum] = "${QCM6490_IDP_SHA256SUM}"
SRC_URI[qcs6490_rb3gen2_core_kit.sha256sum] = "${QCS6490_RB3GEN2_CORE_KIT_SHA256SUM}"
SRC_URI[qcs6490_rb3gen2_industrial_kit.sha256sum] = "${QCS6490_RB3GEN2_INDUSTRIAL_KIT_SHA256SUM}"
SRC_URI[qcs6490_rb3gen2_vision_kit.sha256sum] = "${QCS6490_RB3GEN2_VISION_KIT_SHA256SUM}"
SRC_URI[qcs8300_ride_sx.sha256sum] = "${QCS8300_RIDE_SX_SHA256SUM}"
SRC_URI[qcs9100_ride_sx.sha256sum] = "${QCS9100_RIDE_SX_SHA256SUM}"
SRC_URI[qcs9075_ride_sx.sha256sum] = "${QCS9075_RIDE_SX_SHA256SUM}"
SRC_URI[qcs9075_rb8_core_kit.sha256sum] = "${QCS9_RB8_CORE_KIT_SHA256SUM}"


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
