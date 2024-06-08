inherit qprebuilt pkgconfig

LICENSE          = "Qualcomm-Technologies-Inc.-Proprietary"
LIC_FILES_CHKSUM = "file://${QCOM_COMMON_LICENSE_DIR}${LICENSE};md5=58d50a3d36f27f1a1e6089308a49b403"

DESCRIPTION = "Adreno Graphics"

DEPENDS += "wayland glib-2.0 linux-kernel-qcom-headers property-vault libdmabufheap gbm"

PROVIDES = "  virtual/libgles1 virtual/libgles2 virtual/egl"

SRC_URI[qcm6490.sha256sum] = "ae3fe58fc72562da05a848d456140361482e1f49fc147400e94528145a9a3181"
SRC_URI[qcs9100.sha256sum] = "c7208df1988bc5a248deb8f4d66f4c5f51ab4156694841d28d75c10832e75d25"

SRC_URI = "https://${PBT_ARTIFACTORY}/${PBT_BUILD_ID}/${PBT_BIN_PATH}/${BPN}_${PV}_${PBT_ARCH}.tar.gz;name=${PBT_ARCH}"

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
