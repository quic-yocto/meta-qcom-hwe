#Patches to be applied on top of devicetree
FILESEXTRAPATHS:prepend := "${THISDIR}/devicetree:"

# qcs6490 dtsi patches
SRC_URI:append = " \
    file://qcs6490/0001-arm64-dts-qcom-sc7280-Add-UFS-nodes-for-sc7280-soc.patch \
    file://qcs6490/0001-arm64-dts-qcom-sc7280-Move-video-firmware-to-chrome-.patch \
    file://qcs6490/0001-arm64-dts-qcom-Use-QCOM_SCM_VMID-defines-for-qcom-vm.patch \
    file://qcs6490/0001-UPSTREAM-arm64-dts-qcom-sc7280-Add-capacity-and-DPC-.patch \
    "

# qcs6490 board dts patches
SRC_URI:append = " \
    file://qcs6490/0001-arm64-dts-qcom-Add-base-qcm6490-idp-board-dts.patch \
    file://qcs6490/0002-arm64-dts-qcom-Add-base-qcs6490-rb3gen2-board-dts.patch \
    file://qcs6490/0001-arm64-dts-qcom-qcm6490-idp-Correct-the-voltage-setti.patch \
    file://qcs6490/0002-arm64-dts-qcom-qcs6490-rb3gen2-Correct-the-voltage-s.patch \
    file://qcs6490/0001-arm64-dts-qcom-qcm6490-idp-Update-protected-clocks-l.patch \
    file://qcs6490/0001-arm64-dts-qcom-qcs6490-rb3gen2-Declare-GCC-clocks-pr.patch \
    file://qcs6490/0001-arm64-dts-qcom-qcs6490-rb3gen2-Update-the-LPASS-audi.patch \
    "

# qcs9100 dtsi patches
SRC_URI:append = " \
    file://qcs9100/0001-FROMLIST-arm64-dts-qcom-sa8775p-add-ADSP-CDSP-and-GP.patch \
    file://qcs9100/0001-FROMGIT-arm64-dts-qcom-sa8775p-Mark-APPS-and-PCIe-SM.patch \
    file://qcs9100/0002-BACKPORT-arm64-dts-qcom-sa8775p-Add-llcc-support-for.patch \
    file://qcs9100/0001-UPSTREAM-arm64-dts-qcom-sa8775p-Add-capacity-and-DPC.patch \
    file://qcs9100/0001-UPSTREAM-arm64-dts-qcom-sa8775p-add-TRNG-node.patch \
    file://qcs9100/0001-UPSTREAM-arm64-dts-qcom-sa8775p-enable-the-inline-cr.patch \
    file://qcs9100/0001-BACKPORT-arm64-dts-qcom-sa8775p-add-a-dedicated-memo.patch \
    file://qcs9100/0001-UPSTREAM-arm64-dts-qcom-sa8775p-Add-CPU-and-LLCC-BWM.patch \
    file://qcs9100/0001-UPSTREAM-arm64-dts-qcom-Add-coresight-nodes-for-SA87.patch \
    file://qcs9100/0002-FROMLIST-arm64-dts-qcom-Add-support-for-multimedia-c.patch \
    file://qcs9100/0002-QCLINUX-Revert-FROMLIST-arm64-dts-qcom-sa8775p-add-A.patch \
    file://qcs9100/0005-BACKPORT-arm64-dts-qcom-sa8775p-add-ADSP-CDSP-and-GP.patch \
    file://qcs9100/0001-UPSTREAM-arm64-dts-qcom-sa8775p-fix-the-fastrpc-labe.patch \
    file://qcs9100/0002-UPSTREAM-arm64-qcom-sa8775p-Add-ADSP-and-CDSP0-fastr.patch \
    file://qcs9100/0003-FROMLIST-arm64-dts-qcom-sa8775p-Add-TCSR-halt-regist.patch \
    file://qcs9100/0001-QCLINUX-Revert-UPSTREAM-arm64-dts-qcom-sa8775p-enabl.patch \
    file://qcs9100/0002-FROMLIST-arm64-dts-qcom-sa8775p-pmics-enable-rtc.patch \
    file://qcs9100/0003-FROMGIT-arm64-dts-qcom-sa8775p-ride-enable-pmm8654au.patch \
    file://qcs9100/0001-arm64-dts-qcom-sa8775p-Add-new-memory-map-updates-to.patch \
    file://qcs9100/0007-FROMLIST-arm64-dts-qcom-sa8775p-add-display-dt-nodes.patch \
    file://qcs9100/0006-FROMLIST-arm64-dts-qcom-sa8775p-add-DisplayPort-devi.patch \
    file://qcs9100/0002-PENDING-arm64-dts-qcom-iris-video-driver-for-sa8775p.patch \
    file://qcs9100/0003-FROMLIST-arm64-dts-qcom-sa8775p-Add-gpu-and-gmu-node.patch \
    "
# qcs9100 board dts patches
SRC_URI:append = " \
    file://addons/0001-QCLINUX-arm64-dts-qcom-sa8775p-ride-Add-board-id-and.patch \
    file://qcs9100/0001-FROMLIST-arm64-dts-qcom-sa8775p-ride-enable-remotepr.patch \
    file://workarounds/0001-QCLINUX-arm64-dts-qcom-sa8775p-ride-Change-firmware-.patch \
    file://qcs9100/0004-FROMLIST-arm64-dts-qcom-sa8775p-ride-Add-QCS9100-com.patch \
    file://addons/0002-QCLINUX-arm64-dts-qcom-sa8775p-ride-Add-board-id-and.patch \
    file://qcs9100/0001-FROMLIST-arm64-dts-qcom-Update-sleep_clk-frequency-t.patch \
    file://workarounds/0001-QCLINUX-Revert-QCLINUX-arm64-dts-qcom-sa8775p-ride-C.patch \
    file://qcs9100/0003-QCLINUX-Revert-FROMLIST-arm64-dts-qcom-sa8775p-ride-.patch \
    file://qcs9100/0006-BACKPORT-Enable-all-remoteproc-nodes-on-the-sa8775p-.patch \
    file://qcs9100/0001-FROMLIST-arm64-dts-qcom-sa8775p-ride-add-WiFi-BT-nod.patch \
    file://qcs9100/0001-Revert-FROMLIST-arm64-dts-qcom-sa8775p-ride-add-WiFi.patch \
    file://qcs9100/0003-FROMGIT-BACKPORT-arm64-dts-qcom-move-common-parts-fo.patch \
    file://qcs9100/0004-FROMGIT-arm64-dts-qcom-sa8775p-ride-enable-remotepro.patch \
    file://qcs9100/0005-FROMGIT-arm64-dts-qcom-sa8775p-Add-PCIe-bridge-node.patch \
    file://qcs9100/0006-FROMLIST-BACKPORT-arm64-dts-qcom-sa8775p-ride-add-Wi.patch \
    file://qcs9100/0007-FROMGIT-arm64-dts-qcom-sa8775p-ride-r3-add-new-board.patch \
    file://qcs9100/0010-FROMLIST-arm64-dts-qcom-qcs9100-Add-support-for-the-.patch \
    file://qcs9100/0011-QCLINUX-arm64-dts-qcom-Add-Modify-board-id-and-msm-i.patch \
    file://qcs9100/0007-FROMLIST-arm64-dts-qcom-sa8775p-ride-Enable-Display-.patch \
    "

# qcs615 dtsi patches
SRC_URI:append = " \
    file://qcs615/0020-FROMLIST-arm64-dts-qcom-add-initial-support-for-QCS6.patch \
    file://qcs615/0003-FROMLIST-arm64-dts-qcom-qcs615-add-the-SCM-node.patch \
    file://qcs615/0004-FROMLIST-arm64-dts-qcom-qcs615-add-the-APPS-SMMU-nod.patch \
    file://qcs615/0001-FROMLIST-arm64-dts-qcom-qcs615-Add-LLCC-support-for-.patch \
    file://qcs615/0001-FROMLIST-arm64-dts-qcom-qcs615-Add-QUPv3-configurati.patch \
    file://qcs615/0003-FROMLIST-arm64-dts-qcom-qcs615-add-UFS-node.patch \
    file://qcs615/0003-FROMLIST-arm64-dts-qcom-qcs615-add-ethernet-node.patch \
    file://qcs615/0001-FROMLIST-arm64-dts-qcom-qcs615-add-SDHC1-and-SDHC2.patch \
    "

# qcs615 board dts patches
SRC_URI:append = " \
    file://qcs615/0021-FROMLIST-arm64-dts-qcom-add-base-QCS615-RIDE-dts.patch \
    file://addons/0001-QCLINUX-arm64-dts-qcom-qcs615-ride-add-board-id-and-.patch \
    file://qcs615/0004-FROMLIST-arm64-dts-qcom-qcs615-ride-Enable-UFS-node.patch \
    file://qcs615/0004-FROMLIST-arm64-dts-qcom-qcs615-ride-Enable-ethernet-.patch \
    file://qcs615/0002-FROMLIST-arm64-dts-qcom-qcs615-ride-Enable-SDHC1-and-SDHC2.patch\
    "
# qcs8300 dtsi patches
SRC_URI:append = " \
    file://qcs8300/0025-FROMLIST-arm64-dts-qcom-add-initial-support-for-QCS8.patch \
    file://qcs8300/0005-FROMLIST-arm64-dts-qcom-qcs8300-Add-LLCC-support-for.patch \
    file://qcs8300/0006-FROMLIST-arm64-dts-qcom-qcs8300-Add-PMU-support-for-.patch \
    file://qcs8300/0001-FROMLIST-arm64-dts-qcom-Add-coresight-nodes-for-QCS8.patch \
    "

# qcs8300 board dts patches
SRC_URI:append = " \
    file://qcs8300/0026-FROMLIST-arm64-dts-qcom-add-base-QCS8300-RIDE-dts.patch \
    file://addons/0001-QCLINUX-arm64-dts-qcom-qcs8300-ride-add-board-id-and.patch \
	"
