inherit module

DESCRIPTION = "QCOM Display drivers"

LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/${LICENSE};md5=801f80980d171dd6425610833a22dbe6"

SRCPROJECT = "git://git.codelinaro.org/clo/le/platform/vendor/opensource/display-drivers.git;protocol=https"
SRCBRANCH  = "display-kernel.qclinux.1.0.r2-rel"
SRCREV     = "8ed9fb2d3022fe20fdf2e5b45579a7b3b126122d"

SRC_URI =  "${SRCPROJECT};branch=${SRCBRANCH};destsuffix=display/vendor/qcom/opensource/display-drivers"

S = "${WORKDIR}/display/vendor/qcom/opensource/display-drivers"

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
