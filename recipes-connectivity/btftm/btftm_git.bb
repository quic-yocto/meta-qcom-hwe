inherit qprebuilt pkgconfig

LICENSE          = "Qualcomm-Technologies-Inc.-Proprietary"
LIC_FILES_CHKSUM = "file://${QCOM_COMMON_LICENSE_DIR}${LICENSE};md5=58d50a3d36f27f1a1e6089308a49b403"

DESCRIPTION = "Qualcomm Technologies ftmdaemon"

DEPENDS += "libnl diag glib-2.0 property-vault"

RDEPENDS:${PN} = "property-vault"


SRC_URI[qcm6490.sha256sum] = "a342618851a293ce8e42f68a187942d347ebf27179df0e6fb6c1611c37c21439"
SRC_URI[qcs9100.sha256sum] = "179653844d3f568e179d1717a19bdbb9ecf8472e47c2517fd236f4caaff096d0"

SRC_URI = "https://${PBT_ARTIFACTORY}/${PBT_BUILD_ID}/${PBT_BIN_PATH}/${BPN}_${PV}_${PBT_ARCH}.tar.gz;name=${PBT_ARCH}"

PACKAGE_ARCH = "${MACHINE_ARCH}"
