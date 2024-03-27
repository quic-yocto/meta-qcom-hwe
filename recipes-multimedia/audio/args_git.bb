inherit qprebuilt pkgconfig

LICENSE          = "Qualcomm-Technologies-Inc.-Proprietary"
LIC_FILES_CHKSUM = "file://${QCOM_COMMON_LICENSE_DIR}${LICENSE};md5=58d50a3d36f27f1a1e6089308a49b403"

DESCRIPTION = "AROSP."

DEPENDS += "glib-2.0 virtual/kernel diag diag-router"

RDEPENDS:${PN} = "glib-2.0 diag diag-router"


SRCREV = "dc86a7a99d1bbfca29591ad6e18102c92a1ff5cc"

SRC_URI = "git://qpm-git.qualcomm.com/home2/git/revision-history/qualcomm_linux-spf-1-0-le-qclinux-1-0-r1_api-linux_history_prebuilts.git;protocol=https;branch=LE.QCLINUX.1.0.R1"

PREBUILT_TARBALL = "args_git_${PACKAGE_ARCH}.tar.gz"

S = "${WORKDIR}/git/apps_proc/prebuilt_HY22"

SOLIBS = ".so"
FILES_SOLIBSDEV = ""
