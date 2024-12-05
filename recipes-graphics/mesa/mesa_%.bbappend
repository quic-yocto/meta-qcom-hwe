FILESEXTRAPATHS:prepend:qcom-base-bsp := "${THISDIR}/files:"

SRC_URI:append:qcom-base-bsp = " file://0001-freedreno-Add-support-for-Adreno-663-GPU.patch \
                               "
