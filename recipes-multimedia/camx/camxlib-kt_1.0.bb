inherit qprebuilt pkgconfig

LICENSE          = "Qualcomm-Technologies-Inc.-Proprietary"
LIC_FILES_CHKSUM = "file://${QCOM_COMMON_LICENSE_DIR}${LICENSE};md5=58d50a3d36f27f1a1e6089308a49b403"

DESCRIPTION = "Camx"

DEPENDS += "syslog-plumber property-vault glib-2.0 gbm fastrpc adreno"

RDEPENDS:${PN} += "cameradlkm"


SRC_URI[qcm6490.sha256sum] = "6a4802bb65e8fe145e7792ab935c8a058ef402813dfe382bce76ed2dde988d4f"
SRC_URI[qcs9100.sha256sum] = "2f286e99dfe07db8e81208811b72b0c835b42ffa67856f0982741490a8af0065"

SRC_URI = "https://${PBT_ARTIFACTORY}/${PBT_BUILD_ID}/${PBT_BIN_PATH}/${BPN}_${PV}_${PBT_ARCH}.tar.gz;name=${PBT_ARCH}"

PACKAGE_ARCH = "${MACHINE_ARCH}"

FILES:${PN} = "\
    /usr/lib/* \
    /usr/bin/* \
    /usr/include/* \
    /lib/firmware/*"
FILES:${PN}-dev = ""


INSANE_SKIP:${PN}-dbg = "arch"
INSANE_SKIP:${PN} = "arch"

