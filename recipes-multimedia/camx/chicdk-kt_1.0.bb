inherit qprebuilt

LICENSE          = "Qualcomm-Technologies-Inc.-Proprietary"
LIC_FILES_CHKSUM = "file://${QCOM_COMMON_LICENSE_DIR}${LICENSE};md5=58d50a3d36f27f1a1e6089308a49b403"

DESCRIPTION = "Camx"

DEPENDS += "syslog-plumber property-vault glib-2.0 gbm camx-kt camxapi-kt adreno"

SRC_URI[qcm6490.sha256sum] = "5d0b013a85662f818d5c9727bd3de30c8c19168799ff6d738f450d4227a93d63"
SRC_URI[qcs9100.sha256sum] = "5206666a552723578d8ffb12eb3113e9c4b94e71872eb55563536dd245092168"

SRC_URI = "https://${PBT_ARTIFACTORY}/${PBT_BUILD_ID}/${PBT_BIN_PATH}/${BPN}_${PV}_${PBT_ARCH}.tar.gz;name=${PBT_ARCH}"

PACKAGE_ARCH = "${MACHINE_ARCH}"

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

