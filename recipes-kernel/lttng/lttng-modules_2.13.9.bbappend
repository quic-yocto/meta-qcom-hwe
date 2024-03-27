FILESEXTRAPATHS:prepend := "${THISDIR}/${BPN}-${PV}:"

# Patches to fix compilation errors with kernels above linux 6.3
SRC_URI:append = " \
           file://fix-mm-introduce-vma-vm_flags-wrapper-functions.patch  \
           file://fix-uuid-Decouple-guid_t-and-uuid_le-types-and-respective-macros.patch \
           file://fix-btrfs-pass-find_free_extent_ctl-to-allocator-tracepoints.patch \
           file://fix-net-add-location-to-trace_consume_skb.patch \
           "
