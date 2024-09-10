inherit qprebuilt pkgconfig

LICENSE          = "Qualcomm-Technologies-Inc.-Proprietary"
LIC_FILES_CHKSUM = "file://${QCOM_COMMON_LICENSE_DIR}${LICENSE};md5=58d50a3d36f27f1a1e6089308a49b403"

DESCRIPTION = "Securemsm library with sampleclient used to test sampleapp with qseecom driver through QSEEComApi library"

DEPENDS += "minkipc securemsm-features glib-2.0 glibc linux-kernel-qcom-headers libdmabufheap"

SRC_URI[qcm6490.sha256sum] = "9cb3b54c3e0cd69b4e9033b989f80ee1a77eafef5db441a2533930649a0581b4"
SRC_URI[qcs9100.sha256sum] = "0dcb4ab1a9382238cebaee3346e98112d5a0c8c73ee18fe88507e79b38cc21df"
SRC_URI[qcs8300.sha256sum] = "eb77d10c8d48ae376da6674a5b32ad4a2c953ef236e30133f49f2f372fd5989e"

SRC_URI = "https://${PBT_ARTIFACTORY}/${PBT_BUILD_ID}/${PBT_BIN_PATH}/${BPN}_${PV}_${PBT_ARCH}.tar.gz;name=${PBT_ARCH}"

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

