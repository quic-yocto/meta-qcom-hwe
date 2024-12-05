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
    file://qcs9100/0001-arm64-dts-qcom-sa8775p-Correct-IRQ-number-of-EL2-non.patch \
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
    file://qcs9100/0001-arm64-dts-qcom-sa8775p-Add-new-memory-map-updates-to.patch \
    file://qcs9100/0002-arm64-dts-qcom-sa8775p-Update-memory-map-for-SA8775P.patch \
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
    "
