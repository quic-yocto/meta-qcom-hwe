# do not override KBRANCH and SRCREV_machine, use default ones.
COMPATIBLE_MACHINE:qcom = "(qcom)"

FILESEXTRAPATHS:prepend:qcom := "${THISDIR}/${PN}:"

SRC_URI:append:qcom = " \
    file://qcm6490-board-dts/0001-FROMLIST-arm64-dts-qcom-qcm6490-idp-Update-protected.patch \
    file://qcm6490-board-dts/0001-PENDING-arm64-dts-qcom-qcm6490-Add-UFS-nodes-for-IDP.patch \
    file://qcm6490-board-dts/0001-arm64-dts-qcom-sc7280-don-t-enable-GPU-on-unsupporte.patch \
    file://qcm6490-drivers/0001-firmware-qcom-scm-Introduce-CP_SMMU_APERTURE_ID.patch \
    file://qcm6490-drivers/0002-drm-msm-adreno-Setup-SMMU-aparture-for-per-process-p.patch \
    file://workarounds/0001-QCLINUX-arm64-dts-qcom-qcm6490-disable-sdhc1-for-ufs.patch \
    file://workarounds/0001-PENDING-arm64-dts-qcom-Remove-voltage-vote-support-f.patch \
"

# Include additional kernel configs.
SRC_URI:append:qcom = " \
    file://configs/qcom.cfg \
"

# When a defconfig is provided, the linux-yocto configuration
# uses the filename as a trigger to use a 'allnoconfig' baseline
# before merging the defconfig into the build.
#
# If the defconfig file was created with make_savedefconfig,
# not all options are specified, and should be restored with their
# defaults, not set to 'n'. To properly expand a defconfig like
# this, specify: KCONFIG_MODE="--alldefconfig" in the kernel
# recipe.
KCONFIG_MODE:qcom = "--alldefconfig"

KBUILD_DEFCONFIG:qcom ?= "defconfig"

do_install:append:qcom() {
	sed -i 's:${TMPDIR}::g' ${WORKDIR}/linux-${PACKAGE_ARCH}-${LINUX_KERNEL_TYPE}-build/drivers/gpu/drm/msm/generated/*
}
