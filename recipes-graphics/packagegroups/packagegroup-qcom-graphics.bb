SUMMARY = "QCOM GFX package groups"

PACKAGE_ARCH="${MACHINE_ARCH}"

inherit packagegroup

LICENSE  = "Qualcomm-Technologies-Inc.-Proprietary"

PROVIDES = "${PACKAGES}"

PACKAGES = "${PN}"

VULKAN_LOADER = ""
VULKAN_LOADER:qcm6490 = "True"
VULKAN_LOADER:qcs8550 = "True"

RDEPENDS:${PN} = " \
    adreno \
    ${@oe.utils.conditional('VULKAN_LOADER', 'True', 'vulkan-loader', '', d)} \
    graphicsdevicetree \
    graphicsdlkm \
"