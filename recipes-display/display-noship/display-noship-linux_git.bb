inherit qprebuilt pkgconfig

LICENSE          = "Qualcomm-Technologies-Inc.-Proprietary"
LIC_FILES_CHKSUM = "file://${QCOM_COMMON_LICENSE_DIR}${LICENSE};md5=58d50a3d36f27f1a1e6089308a49b403"

DESCRIPTION = "libsdmextension Library"

DEPENDS += "display-hal-linux jsoncpp glib-2.0 display-ship-linux libdrm openssl linux-kernel-qcom-headers"

SRCREV = "ad944e38851618c4096293e8cb7ea82c4e7e427a"

SRC_URI = "git://qpm-git.qualcomm.com/home2/git/revision-history/qualcomm_linux-spf-1-0-le-qclinux-1-0-r1_api-linux_history_prebuilts.git;protocol=https;branch=LE.QCLINUX.1.0.R1"

PREBUILT_TARBALL = "display-noship-linux_git_qcm6490.tar.gz"

S = "${WORKDIR}/git/apps_proc/prebuilt_HY22"

PACKAGE_ARCH = "${MACHINE_ARCH}"

PACKAGES = "${PN}-dbg ${PN}-dev ${PN}"

FILES:${PN}  += " /usr/data/display/* "
FILES:${PN}  += " ${libdir}/* "
FILES:${PN}-dev  = " ${includedir}/* "
FILES:${PN}-dbg  = " ${libdir}/.debug/* "
