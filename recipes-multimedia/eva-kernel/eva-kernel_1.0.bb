inherit module

DESCRIPTION = "QCOM EVA driver"
LICENSE = "GPLv2.0-with-linux-syscall-note"
LIC_FILES_CHKSUM = "file://${QCOM_COMMON_LICENSE_DIR}${LICENSE};md5=8afb6abdac9a14cb18a0d6c9c151e9b4"

DEPENDS += "synx-kernel-header synx-kernel"

SRC_URI = "git://git.codelinaro.org/clo/le/platform/vendor/opensource/eva-kernel.git;protocol=https;rev=76b98e39bd730d22027e1641d8a01471de280669;branch=eva-kernel.qclinux.1.0.r1-rel;destsuffix=cv/cv-kernel/eva-kernel"
S = "${WORKDIR}/cv/cv-kernel/eva-kernel"

EXTRA_OEMAKE += "INCLUDEDIR=${STAGING_DIR_TARGET}${includedir} SOURCEDIR=${WORKSPACE}"
INSANE_SKIP:${PN} += "installed-vs-shipped"
# INSANE_SKIP allows to omit the packaging of the .conf files which we just want to install on the target

# Inserts the kernel module at boot up
KERNEL_MODULE_AUTOLOAD += "msm_eva"
# Passes the module parameter to the kernel module at boot up
KERNEL_MODULE_PROBECONF += "msm_eva"
module_conf_msm_eva = "options msm_eva mp_load_pil=0"
# More info: https://www.oreilly.com/library/view/embedded-linux-development/9781788399210/79507826-6a98-42f2-811c-38ff1f3f155a.xhtml
