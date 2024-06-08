inherit module

DESCRIPTION = "QCOM Video drivers"
LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/${LICENSE};md5=801f80980d171dd6425610833a22dbe6"

SRC_URI     =  "git://git.codelinaro.org/clo/le/platform/vendor/opensource/video-driver.git;protocol=https;rev=b0eaf8baf478c87581f74ffd83ceac171e39a991;branch=video.qclinux.1.0.r1-rel"

S = "${WORKDIR}/git"

MAKE_TARGETS = "modules"
MODULES_INSTALL_TARGET = "modules_install"

