inherit qprebuilt pkgconfig

LICENSE          = "Qualcomm-Technologies-Inc.-Proprietary"
LIC_FILES_CHKSUM = "file://${QCOM_COMMON_LICENSE_DIR}${LICENSE};md5=58d50a3d36f27f1a1e6089308a49b403"

DESCRIPTION = "securemsm-features with QseecomAPI user space library to interact with qseecom driver"

DEPENDS += "libxml2 libtinyxml2 linux-kernel-qcom-headers glib-2.0 glibc libdmabufheap securemsm-headers minkipc property-vault jsoncpp qmi-framework curl"

SRCREV = "dc86a7a99d1bbfca29591ad6e18102c92a1ff5cc"

SRC_URI = "git://qpm-git.qualcomm.com/home2/git/revision-history/qualcomm_linux-spf-1-0-le-qclinux-1-0-r1_api-linux_history_prebuilts.git;protocol=https;branch=LE.QCLINUX.1.0.R1"

PREBUILT_TARBALL = "securemsm-features_1.0_qcm6490.tar.gz"

S = "${WORKDIR}/git/apps_proc/prebuilt_HY22"

PACKAGE_ARCH = "${MACHINE_ARCH}"

FILES:${PN} += "/usr/bin/*"
FILES:${PN} += "/usr/bin/"
FILES:${PN} += "${bindir}/*"
FILES:${PN} += "${libdir} ${includedir}"
FILES:${PN}-dev = "${libdir}/*.la"


INSANE_SKIP:${PN} = "dev-so"
INSANE_SKIP:${PN} += "dev-deps"
INSANE_SKIP:${PN} += "debug-files"
INSANE_SKIP:${PN} += "file-rdeps"

