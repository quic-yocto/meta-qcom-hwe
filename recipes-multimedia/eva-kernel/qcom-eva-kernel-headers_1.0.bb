inherit autotools-brokensep pkgconfig
DESCRIPTION = "Generates qcom_eva_kernel headers"

LICENSE          = "GPLv2.0-with-linux-syscall-note"
LIC_FILES_CHKSUM = "file://${QCOM_COMMON_LICENSE_DIR}${LICENSE};md5=8afb6abdac9a14cb18a0d6c9c151e9b4"

#PACKAGE_ARCH    ?= "${MACHINE_ARCH}"

SRC_URI += "git://git.codelinaro.org/clo/le/platform/vendor/opensource/eva-kernel.git;protocol=https;rev=aa5d4d1d1a91815fc0508d25b6b0e4d2523cbdd1;branch=eva-kernel.qclinux.1.0.r1-rel;destsuffix=cv/cv-kernel/eva-kernel"

S = "${WORKDIR}/cv/cv-kernel/eva-kernel/include/uapi/eva/media"

ALLOW_EMPTY:${PN} = "1"