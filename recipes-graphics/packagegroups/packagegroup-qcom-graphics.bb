SUMMARY = "QCOM GFX package groups"
LICENSE  = "Qualcomm-Technologies-Inc.-Proprietary"

PACKAGE_ARCH="${SOC_ARCH}"

inherit packagegroup

PROVIDES = "${PACKAGES}"

PACKAGES = "${PN}"

VULKAN_LOADER = ""
VULKAN_LOADER:qcm6490 = "True"
VULKAN_LOADER:qcs8550 = "True"
VULKAN_LOADER:qcs8650 = "True"
VULKAN_LOADER:qcs9100 = "True"
VULKAN_LOADER:qcs8300 = "True"

RDEPENDS:${PN} = " \
    ${GL_PROVIDER} \
    ${@oe.utils.conditional('VULKAN_LOADER', 'True', 'vulkan-loader', '', d)} \
    qcom-graphicsdevicetree \
    qcom-graphicsdlkm \
"

