inherit qprebuilt

LICENSE          = "Qualcomm-Technologies-Inc.-Proprietary"
LIC_FILES_CHKSUM = "file://${QCOM_COMMON_LICENSE_DIR}${LICENSE};md5=58d50a3d36f27f1a1e6089308a49b403"

DESCRIPTION = "Camx"

DEPENDS += "syslog-plumber property-vault glib-2.0 gbm camx-kt adreno"

SRC_URI[sha256sum] = "d74f20f3c1ab33e33612c52fef647d2b74664a7d29535dbc915857f50a7dfb42"

SRC_URI = "https://${PBT_ARTIFACTORY}/${PBT_BUILD_ID}/${PBT_BIN_PATH}/${BPN}_${PV}_${PBT_ARCH}.tar.gz"

PACKAGE_ARCH = "${MACHINE_ARCH}"

do_install:append() {
    dirname=d.getVar('D') + "/var/cache/camera"
    cmd = "mkdir -p %s" %(dirname)
    (retval, output) = oe.utils.getstatusoutput(cmd)
    if retval:
        bb.fatal("could not create camera dir (%s)" % output)
}

pkg_postinst:${PN} () {
    chmod 777 -R $D/var/cache/camera
}

FILES:${PN} = "\
    /usr/lib/* \
    /usr/bin/* \
    /usr/lib/rfsa/adsp/* \
    /usr/include/* \
    /lib/firmware/* \
    /system/etc/camera/* \
    /var/cache/camera "
FILES:${PN}-dev = ""


INSANE_SKIP = "1"
INSANE_SKIP:${PN} = "already-stripped"

