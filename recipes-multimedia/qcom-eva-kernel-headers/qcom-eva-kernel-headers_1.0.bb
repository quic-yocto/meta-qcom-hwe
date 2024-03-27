inherit autotools-brokensep pkgconfig
DESCRIPTION = "Generates qcom_eva_kernel headers"

LICENSE          = "GPLv2.0-with-linux-syscall-note"
LIC_FILES_CHKSUM = "file://${QCOM_COMMON_LICENSE_DIR}${LICENSE};md5=8afb6abdac9a14cb18a0d6c9c151e9b4"

#PACKAGE_ARCH    ?= "${MACHINE_ARCH}"

SRC_URI   = "git://git.codelinaro.org/clo/le/platform/vendor/opensource/eva-kernel.git;protocol=https;rev=76b98e39bd730d22027e1641d8a01471de280669;branch=eva-kernel.qclinux.1.0.r1-rel;destsuffix=cv/cv-kernel/eva-kernel"

S = "${WORKDIR}/cv/cv-kernel/eva-kernel/include/uapi/eva/media"

ALLOW_EMPTY:${PN} = "1"
