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
    "
