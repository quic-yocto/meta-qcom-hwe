inherit qprebuilt pkgconfig

LICENSE          = "Qualcomm-Technologies-Inc.-Proprietary"
LIC_FILES_CHKSUM = "file://${QCOM_COMMON_LICENSE_DIR}${LICENSE};md5=58d50a3d36f27f1a1e6089308a49b403"

DESCRIPTION = "Camx"

DEPENDS += "syslog-plumber property-vault glib-2.0 gbm fastrpc"

RDEPENDS:${PN} += "cameradlkm"

SRCREV = "587ec9a9723f9fe87921c998e1a53420dd3eb809"

SRC_URI = "git://qpm-git.qualcomm.com/home2/git/revision-history/qualcomm_linux-spf-1-0-le-qclinux-1-0-r1_api-linux_history_prebuilts.git;protocol=https;branch=LE.QCLINUX.1.0.R1"

PREBUILT_TARBALL = "camxlib_1.0_${PACKAGE_ARCH}.tar.gz"

S = "${WORKDIR}/git/apps_proc/prebuilt_HY22"

PACKAGE_ARCH = "${MACHINE_ARCH}"

FILES:${PN} = "\
    /usr/lib/* \
    /usr/bin/* \
    /usr/include/* \
    /data/* \
    /lib/firmware/* \
    /vendor/*"

FILES:${PN}-dev = ""

INSANE_SKIP:${PN}:append = " arch"
INSANE_SKIP:${PN}-dbg:append = " arch"
