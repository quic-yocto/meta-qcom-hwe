inherit module

DESCRIPTION = "QCOM Camera drivers"
LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/${LICENSE};md5=801f80980d171dd6425610833a22dbe6"

DEPENDS += "linux-kernel-headers-install-native"

SRC_URI     =  "git://git.codelinaro.org/clo/le/platform/vendor/opensource/camera-kernel.git;protocol=https;rev=aefae9a7897a2ca713b60472920a6c647a8962be;branch=camera-kernel.qclinux.1.0.r1-rel;destsuffix=vendor/qcom/opensource/camera-kernel"

S = "${WORKDIR}/vendor/qcom/opensource/camera-kernel"

MODULES_INSTALL_TARGET = "modules_install headers_install"
CAMERA_ARCH ?= "all"
CAMERA_ARCH:qcm6490 ?= "qcm6490"
HEADERS_DIR ?= "camera"
HEADERS_DIR:qcm6490 ?= "camera_kt"
EXTRA_OEMAKE += "CAMERA_ARCH='${CAMERA_ARCH}' MACHINE='${MACHINE}' HEADERS_DIR='${HEADERS_DIR}'"

do_install:append() {
	install -d ${D}${includedir}/media
	install -m 0755 ${B}/sanitized_headers/camera/media/*.h -D ${D}${includedir}/media/
}
