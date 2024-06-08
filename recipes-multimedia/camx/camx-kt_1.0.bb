inherit qprebuilt pkgconfig

LICENSE          = "Qualcomm-Technologies-Inc.-Proprietary"
LIC_FILES_CHKSUM = "file://${QCOM_COMMON_LICENSE_DIR}${LICENSE};md5=58d50a3d36f27f1a1e6089308a49b403"

DESCRIPTION = "Camx"

DEPENDS += "syslog-plumber property-vault glib-2.0 gbm camxlib-kt cameradlkm fastrpc sensors-ship-qti qmi-framework"

SRC_URI[sha256sum] = "58fc0b29d4923c7b375c9bbb6df9aee2afe75e8181bd665cf4e04037b1fed63e"

SRC_URI = "https://${PBT_ARTIFACTORY}/${PBT_BUILD_ID}/${PBT_BIN_PATH}/${BPN}_${PV}_${PBT_ARCH}.tar.gz;name=${PBT_ARCH}"

PACKAGE_ARCH = "${MACHINE_ARCH}"

FILES:${PN} = "\
    /usr/lib/* \
    /usr/bin/* \
    /usr/include/* \
    /lib/firmware/*"
FILES:${PN}-dev = ""


INSANE_SKIP = "1"
INSANE_SKIP:${PN} = "dev-so"

