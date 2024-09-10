# Patches to be applied on top of kernel drivers
FILESEXTRAPATHS:prepend := "${THISDIR}/drivers:"

SRC_URI:append = " \
    file://qcs6490/0001-phy-qcom-qmp-ufs-Add-Phy-Configuration-support-for-S.patch \
    file://qcs6490/0001-FROMGIT-soc-qcom-cmd-db-Map-shared-memory-as-WC-not-.patch \
    file://qcs9100/0001-FROMLIST-remoteproc-qcom_q6v5_pas-Add-support-for-SA.patch \
    file://qcs9100/0002-FROMLIST-soc-qcom-socinfo-add-QCS9100-ID.patch \
    file://qcs9100/0002-BACKPORT-soc-qcom-llcc-Add-llcc-configuration-suppor.patch \
    file://qcs9100/0001-UPSTREAM-crypto-qcom-rng-Add-hw_random-interface-sup.patch \
    file://qcs9100/0002-BACKPORT-firmware-qcom-add-a-dedicated-TrustZone-buf.patch \
    file://qcs9100/0003-BACKPORT-firmware-qcom-scm-enable-the-TZ-mem-allocat.patch \
    file://qcs9100/0004-BACKPORT-firmware-qcom-scm-smc-switch-to-using-the-S.patch \
    file://qcs9100/0005-BACKPORT-firmware-qcom-scm-make-qcom_scm_assign_mem-.patch \
    file://qcs9100/0006-BACKPORT-firmware-qcom-scm-make-qcom_scm_ice_set_key.patch \
    file://qcs9100/0007-BACKPORT-firmware-qcom-scm-make-qcom_scm_lmh_dcvsh-u.patch \
    file://qcs9100/0008-BACKPORT-firmware-qcom-scm-add-support-for-SHM-bridg.patch \
    file://qcs9100/0009-BACKPORT-firmware-qcom-tzmem-enable-SHM-Bridge-suppo.patch \
    file://qcs9100/0010-BACKPORT-firmware-qcom-scm-add-support-for-SHM-bridg.patch \
    file://qcs9100/0011-BACKPORT-firmware-qcom-scm-clarify-the-comment-in-qc.patch \
    file://qcs9100/0001-UPSTREAM-firmware-qcom-tzmem-don-t-ask-about-allocat.patch \
    file://qcs9100/0001-UPSTREAM-firmware-qcom-tzmem-export-devm_qcom_tzmem_.patch \
    file://qcs9100/0001-UPSTREAM-firmware-qcom-tzmem-simplify-returning-poin.patch \
    "
