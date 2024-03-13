inherit qprebuilt pkgconfig

LICENSE          = "Qualcomm-Technologies-Inc.-Proprietary"
LIC_FILES_CHKSUM = "file://${QCOM_COMMON_LICENSE_DIR}${LICENSE};md5=58d50a3d36f27f1a1e6089308a49b403"

DESCRIPTION = "sva-ml-common"

DEPENDS += "sva-common"

SRCREV = "c738c1a85e20a7182bab084597ae5adef2df03cd"

SRC_URI = "git://qpm-git.qualcomm.com/home2/git/revision-history/qualcomm_linux-spf-1-0-le-qclinux-1-0-r1_api-linux_history_prebuilts.git;protocol=https;branch=LE.QCLINUX.1.0.R1"

PREBUILT_TARBALL = "sva-ml-commondwarf2-3_git_${PACKAGE_ARCH}.tar.gz"

S = "${WORKDIR}/git/apps_proc/prebuilt_HY22"
