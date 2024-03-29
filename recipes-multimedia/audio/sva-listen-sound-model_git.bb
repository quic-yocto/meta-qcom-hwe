inherit qprebuilt pkgconfig

LICENSE          = "Qualcomm-Technologies-Inc.-Proprietary"
LIC_FILES_CHKSUM = "file://${QCOM_COMMON_LICENSE_DIR}${LICENSE};md5=58d50a3d36f27f1a1e6089308a49b403"

DESCRIPTION = "sva-listen-sound-model"

DEPENDS += "sva-statereorder sva-listen-sound-model-headers sva-common sva-listen-common sva-gmm sva-swmad sva-uv sva-xs sva-eai sva-eai-utils sva-kwd-enroll sva-aml sva-ml-commondwarf2 sva-ml-commondwarf2-3 sva-paramparser sva-sub-lib sva-udk"

SRCREV = "dc86a7a99d1bbfca29591ad6e18102c92a1ff5cc"

SRC_URI = "git://qpm-git.qualcomm.com/home2/git/revision-history/qualcomm_linux-spf-1-0-le-qclinux-1-0-r1_api-linux_history_prebuilts.git;protocol=https;branch=LE.QCLINUX.1.0.R1"

PREBUILT_TARBALL = "sva-listen-sound-model_git_armv8-2a.tar.gz"

S = "${WORKDIR}/git/apps_proc/prebuilt_HY22"

SOLIBS = ".so"
FILES_SOLIBSDEV = ""
