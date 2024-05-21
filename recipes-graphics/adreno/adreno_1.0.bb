inherit qprebuilt pkgconfig

LICENSE          = "Qualcomm-Technologies-Inc.-Proprietary"
LIC_FILES_CHKSUM = "file://${QCOM_COMMON_LICENSE_DIR}${LICENSE};md5=58d50a3d36f27f1a1e6089308a49b403"

DESCRIPTION = "Adreno Graphics"

DEPENDS += "wayland glib-2.0 linux-kernel-qcom-headers property-vault libdmabufheap gbm"

PROVIDES = "  virtual/libgles1 virtual/libgles2 virtual/egl"

SRC_URI[sha256sum] = "bc60fc3de3797734638055d14c397392369d8d4d890c1b0df2988328bb04cb28"

SRC_URI = "https://${PBT_ARTIFACTORY}/${PBT_BUILD_ID}/${PBT_BIN_PATH}/${BPN}_${PV}_${PBT_ARCH}.tar.gz"

PACKAGE_ARCH = "${MACHINE_ARCH}"

FILES:${PN} = "${includedir}/* \
               ${nonarch_base_libdir}/firmware/* \
               ${nonarch_libdir}/* \
               ${base_libdir}/firmware/* \
               ${libdir}/* \
               ${bindir}/* \
               /usr/local/share/vulkan/icd.d/* "
FILES:${PN}-dev = ""
FILES:${PN}-dbg = ""

INSANE_SKIP:${PN} = "installed-vs-shipped dev-deps file-rdeps dev-so arch already-stripped"
