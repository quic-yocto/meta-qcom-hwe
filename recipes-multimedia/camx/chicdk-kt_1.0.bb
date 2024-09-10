inherit qprebuilt

LICENSE          = "Qualcomm-Technologies-Inc.-Proprietary"
LIC_FILES_CHKSUM = "file://${QCOM_COMMON_LICENSE_DIR}${LICENSE};md5=58d50a3d36f27f1a1e6089308a49b403"

DESCRIPTION = "Camx"

DEPENDS += "syslog-plumber glib-2.0 gbm property-vault camx-kt camxapi-kt adreno"

SRC_URI[qcm6490.sha256sum] = "7b0d8aa88df578f6af5820afb6ec474242ccc27d470c7ae82ade6b8191e18d47"



SRC_URI = "https://${PBT_ARTIFACTORY}/${PBT_BUILD_ID}/${PBT_BIN_PATH}/${BPN}_${PV}_${PBT_ARCH}.tar.gz;name=${PBT_ARCH}"

FILES:${PN} = "\
    /usr/lib/* \
    /usr/bin/* \
    /usr/lib/rfsa/adsp/* \
    /usr/include/* \
    /lib/firmware/* \
    /system/etc/camera/* \
    /var/cache/camera "
FILES:${PN}-dev = ""

pkg_postinst:${PN} () {
    chmod 777 -R $D/var/cache/camera
}

INSANE_SKIP = "1"
INSANE_SKIP:${PN} = "already-stripped"

