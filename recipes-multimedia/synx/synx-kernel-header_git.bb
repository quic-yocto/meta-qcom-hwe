inherit autotools

DESCRIPTION = "SYNX FRAMEWORK DRIVER"

LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/${LICENSE};md5=801f80980d171dd6425610833a22dbe6"


SRC_URI  = "git://git.codelinaro.org/clo/le/platform/vendor/opensource/synx-kernel.git;protocol=https;rev=3d32d46edf68f058e3fa045dac8ed6f5b23066aa;branch=synx-kernel.lnx.1.2.r1-rel;destsuffix=synx-kernel"

S = "${WORKDIR}/synx-kernel/include/uapi/synx/media"

do_compile[noexec] = "1"

ALLOW_EMPTY_${PN} = "1"

PACKAGE_ARCH    ?= "${MACHINE_ARCH}"
do_install(){
    install -d ${D}${includedir}
   install -m 0755 ${S}/synx_header.h  ${D}${includedir}
}
