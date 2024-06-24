inherit qprebuilt pkgconfig

LICENSE          = "Qualcomm-Technologies-Inc.-Proprietary"
LIC_FILES_CHKSUM = "file://${QCOM_COMMON_LICENSE_DIR}${LICENSE};md5=58d50a3d36f27f1a1e6089308a49b403"

DESCRIPTION = "Securemsm library with sampleclient used to test sampleapp with qseecom driver through QSEEComApi library"

DEPENDS += "minkipc securemsm-features glib-2.0 glibc linux-kernel-qcom-headers libdmabufheap"

SRC_URI[qcm6490.sha256sum] = "11f64a0c4e2e43136ce8deeb479d5f5a4cc6d167b8eee096f05771d7072329d7"
SRC_URI[qcs9100.sha256sum] = "bbf57171b478edb07e6bdd5531efce16caf10b60c0c9035894563dafb056a808"

SRC_URI = "https://${PBT_ARTIFACTORY}/${PBT_BUILD_ID}/${PBT_BIN_PATH}/${BPN}_${PV}_${PBT_ARCH}.tar.gz;name=${PBT_ARCH}"

PACKAGE_ARCH = "${MACHINE_ARCH}"

do_install:append() {
    dirname=d.getVar('D') + "/var/cache/qwes"
    cmd = "mkdir -p %s" %(dirname)
    (retval, output) = oe.utils.getstatusoutput(cmd)
    if retval:
        bb.fatal("could not create camera dir (%s)" % output)
}

FILES:${PN} += "/usr/bin/*"
FILES:${PN} += "${bindir}/* /var/cache/*"

INSANE_SKIP:${PN} += "debug-files"
