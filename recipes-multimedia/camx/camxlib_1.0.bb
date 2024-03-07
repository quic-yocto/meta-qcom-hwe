inherit qprebuilt pkgconfig

LICENSE          = "Qualcomm-Technologies-Inc.-Proprietary"
LIC_FILES_CHKSUM = "file://${QCOM_COMMON_LICENSE_DIR}${LICENSE};md5=58d50a3d36f27f1a1e6089308a49b403"

DESCRIPTION = "Camx"

DEPENDS += "syslog-plumber glib-2.0 gbm property-vault camxcommon cameradlkm fastrpc"

SRC_URI[qcm6490.sha256sum] = "None"
SRC_URI[qcs9100.sha256sum] = "d6066e38614ec375cc88ea0bd55c723603aaa9f9af4f324d2535e434191b8335"
SRC_URI[qcs8300.sha256sum] = "3ecff4ba31bf476554705fc88d4530a36cd6e7b3f47d13bc7adf4a8b83789ea3"

SRC_URI = "https://${PBT_ARTIFACTORY}/${PBT_BUILD_ID}/${PBT_BIN_PATH}/${BPN}_${PV}_${PBT_ARCH}.tar.gz;name=${PBT_ARCH}"

FILES:${PN} = "\
    /usr/lib/* \
    /usr/bin/* \
    /usr/include/* \
    /lib/firmware/*"

FILES:${PN}-dev = ""

#Skips check for archtecture
INSANE_SKIP:${PN}-dbg = "arch"
INSANE_SKIP:${PN} = "already-stripped"
