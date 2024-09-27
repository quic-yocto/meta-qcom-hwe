# Patches for documentation of DT bindings.
FILESEXTRAPATHS:prepend := "${THISDIR}/dt-bindings:"

SRC_URI:append = " \
    file://qcs6490/0001-dt-bindings-phy-Add-QMP-UFS-PHY-comptible-for-SC7280.patch \
    file://qcs6490/0001-dt-bindings-arm-qcom-Add-QCM6490-Fairphone-5.patch \
    file://qcs6490/0001-dt-bindings-arm-qcom-Add-QCM6490-IDP-and-QCS6490-RB3.patch \
    file://qcs9100/0001-UPSTREAM-dt-bindings-remoteproc-qcom-sa8775p-pas-Doc.patch \
    file://qcs9100/0002-FROMLIST-dt-bindings-mailbox-qcom-ipcc-Add-GPDSP0-an.patch \
    file://qcs9100/0001-FROMLIST-dt-bindings-arm-qcom-ids-add-SoC-ID-for-QCS.patch \
    file://qcs9100/0003-FROMLIST-dt-bindings-arm-qcom-Document-QCS9100-compa.patch \
    file://qcs9100/0003-BACKPORT-dt-bindings-cache-qcom-llcc-Add-SA8775p-des.patch \
    file://qcs9100/0001-UPSTREAM-dt-bindings-crypto-qcom-prng-document-that-.patch \
    file://qcs9100/0001-BACKPORT-dt-bindings-firmware-qcom-scm-add-memory-re.patch \
    file://qcs9100/0001-FROMLIST-dt-bindings-clock-qcom-Add-SA8775P-video-cl.patch \
    file://qcs9100/0002-FROMLIST-dt-bindings-clock-qcom-Add-SA8775P-camera-clock-cont.patch \
    file://qcs9100/0003-FROMLIST-dt-bindings-clock-qcom-Add-SA8775P-display-clock-con.patch \
    file://qcs9100/0001-FROMLIST-dt-bindings-media-Add-sm8550-dt-schema.patch \
    file://qcs9100/0005-QCLINUX-Revert-FROMLIST-dt-bindings-mailbox-qcom-ipc.patch \
    file://qcs9100/0001-UPSTREAM-dt-bindings-misc-qcom-fastrpc-increase-the-.patch \
    file://qcs9100/0002-UPSTREAM-dt-bindings-mailbox-qcom-ipcc-Add-GPDSP0-an.patch \
    file://qcs9100/0003-FROMLIST-dt-bindings-misc-qcom-fastrpc-document-new-.patch \
    file://qcs9100/0005-UPSTREAM-dt-bindings-display-msm-Document-MDSS-on-X1.patch \
    file://qcs9100/0006-UPSTREAM-dt-bindings-phy-qcom-edp-Add-X1E80100-PHY-c.patch \
    file://qcs9100/0001-UPSTREAM-regulator-dt-bindings-describe-the-PMU-modu.patch \
    file://qcs9100/0002-UPSTREAM-dt-bindings-net-wireless-qcom-ath11k-descri.patch \
    file://qcs9100/0004-FROMLIST-dt-bindings-mfd-qcom-tcsr-Add-compatible-fo.patch \
    "
