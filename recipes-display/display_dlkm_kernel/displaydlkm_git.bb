inherit module

DESCRIPTION = "QCOM Display drivers"

LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/${LICENSE};md5=801f80980d171dd6425610833a22dbe6"

SRC_URI += "git://git.codelinaro.org/clo/le/platform/vendor/opensource/display-drivers.git;protocol=https;rev=e1b6c4ed3c204947a9082ac05b41db20bf463e9e;branch=display-kernel.qclinux.1.0.r2-rel"

S = "${WORKDIR}/git"

EXTRA_OEMAKE += "MACHINE='${MACHINE}'"

do_install:append() {
	install -d ${D}/usr/include/
	install -d ${D}/usr/include/display/drm
	install -d ${D}/usr/include/display/hdcp
	install -d ${D}/usr/include/display/media
	install -m 0755 ${B}/include/uapi/display/drm/*.h -D ${D}${includedir}/display/drm/
	install -m 0755 ${B}/include/uapi/display/hdcp/*.h -D ${D}${includedir}/display/hdcp/
	install -m 0755 ${B}/include/uapi/display/media/*.h -D ${D}${includedir}/display/media/
}

RPROVIDES:${PN} += "kernel-module-displaydlkm"
MAKE_TARGETS = "modules"
MODULES_INSTALL_TARGET = "modules_install"