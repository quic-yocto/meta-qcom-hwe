inherit qprebuilt pkgconfig systemd

LICENSE          = "Qualcomm-Technologies-Inc.-Proprietary"
LIC_FILES_CHKSUM = "file://${QCOM_COMMON_LICENSE_DIR}${LICENSE};md5=58d50a3d36f27f1a1e6089308a49b403"

DESCRIPTION = "Sensors-ship Library"

DEPENDS += "glib-2.0 property-vault syslog-plumber qmi-framework diag protobuf fastrpc libdmabufheap"

SRCREV = "87e9e28a5714ffa7cb291eb480b61b8ac6a59c49"

SRC_URI = "git://qpm-git.qualcomm.com/home2/git/revision-history/qualcomm_linux-spf-1-0-le-qclinux-1-0-r1_api-linux_history_prebuilts.git;protocol=https;branch=LE.QCLINUX.1.0.R1"

PREBUILT_TARBALL = "sensors-ship-qti_7.0_qcm6490.tar.gz"

S = "${WORKDIR}/git/apps_proc/prebuilt_HY22"


SYSTEMD_SERVICE:${PN} += "sscrpcd.service"

#Disable the split of debug information into -dbg files
INHIBIT_PACKAGE_DEBUG_SPLIT = "1"

#Skips check for .so symlinks
INSANE_SKIP:${PN} = "dev-so"

# need to export these variables for python-config to work
FILES:${PN} += "${includedir}/*"
FILES:${PN} += "/usr/lib/*"
FILES:${PN} += "/usr/bin/*"
FILES:${PN}-dev  = "${libdir}/*.la ${includedir}"
FILES:${PN} += "${systemd_unitdir}/system/"
FILES:${PN} += "/etc/sensors/*"

SOLIBS = ".so"
FILES:SOLIBSDEV = ""

PACKAGE_ARCH = "${MACHINE_ARCH}"
