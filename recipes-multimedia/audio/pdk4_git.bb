inherit qprebuilt pkgconfig

LICENSE          = "Qualcomm-Technologies-Inc.-Proprietary"
LIC_FILES_CHKSUM = "file://${QCOM_COMMON_LICENSE_DIR}${LICENSE};md5=58d50a3d36f27f1a1e6089308a49b403"

DESCRIPTION = "pdk4"

DEPENDS += "sva-common capiv2-headers sva-ml-commondwarf sva-ml-commondwarf2 sva-ml-commondwarf2-3 sva-eai sva-eai-utils"

SRCREV = "dc86a7a99d1bbfca29591ad6e18102c92a1ff5cc"

SRC_URI = "git://qpm-git.qualcomm.com/home2/git/revision-history/qualcomm_linux-spf-1-0-le-qclinux-1-0-r1_api-linux_history_prebuilts.git;protocol=https;branch=LE.QCLINUX.1.0.R1"

PREBUILT_TARBALL = "pdk4_git_armv8-2a.tar.gz"

S = "${WORKDIR}/git/apps_proc/prebuilt_HY22"

SOLIBS = ".so"
FILES_SOLIBSDEV = ""
