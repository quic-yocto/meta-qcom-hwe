inherit qprebuilt pkgconfig

LICENSE          = "Qualcomm-Technologies-Inc.-Proprietary"
LIC_FILES_CHKSUM = "file://${QCOM_COMMON_LICENSE_DIR}${LICENSE};md5=58d50a3d36f27f1a1e6089308a49b403"

DESCRIPTION = "Adreno Graphics"

DEPENDS += "wayland glib-2.0 linux-kernel-qcom-headers property-vault libdmabufheap gbm"

PROVIDES = "virtual/libgles1 virtual/libgles2 virtual/egl"

SRCREV = "9d16a54e57fc1802f5b81361efc92c423415c4b1"

SRC_URI = "git://qpm-git.qualcomm.com/home2/git/revision-history/qualcomm_linux-spf-1-0-le-qclinux-1-0-r1_api-linux_history_prebuilts.git;protocol=https;branch=LE.QCLINUX.1.0.R1"

PREBUILT_TARBALL = "adreno_1.0_${PACKAGE_ARCH}.tar.gz"

S = "${WORKDIR}/git/apps_proc/prebuilt_HY22"

PACKAGE_ARCH = "${MACHINE_ARCH}"

RPROVIDES:${PN} += "virtual/libgles1 virtual/libgles2 virtual/egl"

INSANE_SKIP:${PN} = "dev-deps file-rdeps dev-so arch"

FILES:${PN} = "${includedir}/* \
               ${nonarch_base_libdir}/firmware/* \
               ${nonarch_libdir}/* \
               ${base_libdir}/firmware/* \
               ${libdir}/* \
               /usr/local/share/vulkan/icd.d/* "

FILES:${PN}-dev = ""
