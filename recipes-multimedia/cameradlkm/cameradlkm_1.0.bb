DESCRIPTION = "QCOM Camera drivers"
LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/files/common-licenses/${LICENSE};md5=801f80980d171dd6425610833a22dbe6"

inherit module

DEPENDS += "linux-kernel-headers-install-native"

SRC_URI = "git://git.codelinaro.org/clo/le/platform/vendor/opensource/camera-kernel.git;protocol=https;rev=8b6ce539ff5648181020b0a509a4a22d464dd281;branch=camera-kernel.qclinux.1.0.r1-rel"
                                                                                               
S = "${WORKDIR}/git"

MODULES_INSTALL_TARGET = "modules_install headers_install"
CAMERA_ARCH ?= "all"
CAMERA_ARCH:qcm6490 ?= "qcm6490"
EXTRA_OEMAKE += "CAMERA_ARCH='${CAMERA_ARCH}' MACHINE='${MACHINE}'"

do_install:append() {
	install -d ${D}${includedir}/media
	install -m 0755 ${B}/sanitized_headers/camera/media/*.h -D ${D}${includedir}/media/
}
