FILESEXTRAPATHS:append := "${THISDIR}:"
FILESEXTRAPATHS:append := "${THISDIR}/libsemanage:"

#Patches
SRC_URI += "file://0996-QCLINUX-libsemanage-Modify-store-root-path.patch"
