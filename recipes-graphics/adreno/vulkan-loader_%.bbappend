FILESEXTRAPATHS:prepend := "${THISDIR}/files:"

SRC_URI += "file://0001-Add-VkSharedPresentSurfaceCapabilitiesKHR_to_vkGetPhysicalDeviceSurfaceCapabilities2KHR.patch;patch=1"
RRECOMMENDS:${PN}:remove = "mesa-vulkan-drivers"
