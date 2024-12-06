inherit module

DESCRIPTION = "QCOM Video drivers"
LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/${LICENSE};md5=801f80980d171dd6425610833a22dbe6"

SRCPROJECT = "git://git.codelinaro.org/clo/le/platform/vendor/opensource/video-driver.git;protocol=https"
SRCBRANCH  = "video.qclinux.1.0.r1-rel"
SRCREV     = "ab1cb3da31b9348d3fab9d97912de3fed9d615d4"

SRC_URI =  "${SRCPROJECT};branch=${SRCBRANCH};destsuffix=vendor/qcom/opensource/video-driver"

S = "${WORKDIR}/vendor/qcom/opensource/video-driver"

MAKE_TARGETS = "modules"
MODULES_INSTALL_TARGET = "modules_install"

