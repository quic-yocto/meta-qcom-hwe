HOMEPAGE = "http://www.denx.de/wiki/U-Boot/WebHome"
DESCRIPTION = "U-Boot, a boot loader for Embedded boards based on PowerPC, \
ARM, MIPS and several other processors, which can be installed in a boot \
ROM and used to initialize and test the hardware or to download and run \
application code."
SECTION = "bootloaders"
DEPENDS += "flex-native bison-native python3-setuptools-native"

LICENSE = "GPL-2.0-or-later"
LIC_FILES_CHKSUM = "file://Licenses/README;md5=2ca5f2c35c8cc335f0a19756634782f1"
PE = "1"

# We use the revision in order to avoid having to fetch it from the
# repo during parse
#SRCREV = "f919c3a889f0ec7d63a48b5d0ed064386b0980bd"
SRCREV = "f39199e8096dab68fe4a8e15f8ff60026a5fe491"

SRC_URI = "git://source.denx.de/u-boot/u-boot.git;protocol=https;branch=master"
SRC_URI += "file://0001-PENDING-arm64-dts-qcom-qcs9100-Add-support-for-the-Q.patch \
	    file://0002-PENDING-configs-add-qcm6490_defconfig.patch \
	    file://0003-PENDING-clk-qcom-add-initial-clock-driver-for-sa8775.patch \
	    file://0004-PENDING-qcom_defconfig-enable-SA8775P-clocks.patch \
	    file://0005-PENDING-arm64-dts-qcom-qcs9100-Add-memory-entry-to-b.patch \
	    file://0006-PENDING-phy-qcom-Import-QMP-phy-related-header-files.patch \
	    file://0007-PENDING-phy-qcom-Add-QMP-UFS-PHY-driver-for-QCS9100.patch \
	    file://0008-PENDING-qcom_defconfig-Enable-QCOM-UFS-and-PHY.patch \
	    file://0009-PENDING-smem-Added-socinfo-header-file.patch \
	    file://0010-PENDING-arm64-dts-qcom-qcs9100-Added-memory-region-f.patch \
	    file://0011-PENDING-smem-Updated-smem-driver-to-support-QCS9100.patch \
	    file://0012-PENDING-efi_loader-Add-multidt-support.patch"

S = "${WORKDIR}/git"
B = "${WORKDIR}/build"

inherit pkgconfig

do_configure[cleandirs] = "${B}"
