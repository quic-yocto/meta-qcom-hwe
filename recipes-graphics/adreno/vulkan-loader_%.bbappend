FILESEXTRAPATHS:prepend:qcom-custom-bsp := "${THISDIR}/files:"

SRC_URI:append:qcom-custom-bsp = " file://0001-Add-VkSharedPresentSurfaceCapabilitiesKHR_to_vkGetPhysicalDeviceSurfaceCapabilities2KHR.patch;patch=1"
RRECOMMENDS:${PN}:remove:qcom-custom-bsp = "mesa-vulkan-drivers"
