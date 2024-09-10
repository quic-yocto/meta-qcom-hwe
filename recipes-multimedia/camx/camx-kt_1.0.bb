inherit qprebuilt pkgconfig

LICENSE          = "Qualcomm-Technologies-Inc.-Proprietary"
LIC_FILES_CHKSUM = "file://${QCOM_COMMON_LICENSE_DIR}${LICENSE};md5=58d50a3d36f27f1a1e6089308a49b403"

DESCRIPTION = "Camx"

DEPENDS += "syslog-plumber glib-2.0 gbm property-vault camxlib-kt cameradlkm fastrpc sensors-ship-qti qmi-framework"

SRC_URI[qcm6490.sha256sum] = "c8394a06902f2f02516e772fbe61d22b1ba2c98f0cdda1f670804f33dba39c2a"
SRC_URI[qcs9100.sha256sum] = "ad15f50d776729c3d26afe8c1279fe74596c108dbfdd4ed485f179b2eb22f4de"

SRC_URI = "https://${PBT_ARTIFACTORY}/${PBT_BUILD_ID}/${PBT_BIN_PATH}/${BPN}_${PV}_${PBT_ARCH}.tar.gz;name=${PBT_ARCH}"

FILES:${PN} = "\
    /usr/lib/* \
    /usr/bin/* \
    /usr/include/* \
    /lib/firmware/*"
FILES:${PN}-dev = ""


INSANE_SKIP = "1"
INSANE_SKIP:${PN} = "dev-so"

