SUMMARY = "QCOM GFX package groups"

PACKAGE_ARCH="${MACHINE_ARCH}"

inherit packagegroup

LICENSE  = "Qualcomm-Technologies-Inc.-Proprietary"

PROVIDES = "${PACKAGES}"

PACKAGES = " \
            packagegroup-qcom-graphics \
           "

VULKAN_LOADER = ""
VULKAN_LOADER:qcm6490 = "True"

RDEPENDS:packagegroup-qcom-graphics = " \
                                       adreno \
                                       ${@oe.utils.conditional('VULKAN_LOADER', 'True', 'vulkan-loader', '', d)} \
                                       graphicsdlkm \
                                       graphicsdevicetree \
                                      "
