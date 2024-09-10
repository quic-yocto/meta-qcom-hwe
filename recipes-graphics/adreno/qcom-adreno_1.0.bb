inherit qprebuilt pkgconfig

LICENSE          = "Qualcomm-Technologies-Inc.-Proprietary"
LIC_FILES_CHKSUM = "file://${QCOM_COMMON_LICENSE_DIR}${LICENSE};md5=58d50a3d36f27f1a1e6089308a49b403"

DESCRIPTION = "Adreno Graphics"

DEPENDS += "wayland glib-2.0 linux-kernel-qcom-headers property-vault libdmabufheap gbm"

PROVIDES = "  virtual/libgles1 virtual/libgles2 virtual/egl adreno"

SRC_URI[qcm6490.sha256sum] = "12df550361ec10213f0fd408bb95e655cd3e623de04055391bc9e56c1c46e445"
SRC_URI[qcs9100.sha256sum] = "26e576c447c0ed12316bb335a8b971b458597d015d941d4e6aa99b1b1b12d3e5"
SRC_URI[qcs8300.sha256sum] = "86e4382ad612c940adcff28e32edb19ac03e47c4d966ac16831ca9e70c9ea64c"

SRC_URI = "https://${PBT_ARTIFACTORY}/${PBT_BUILD_ID}/${PBT_BIN_PATH}/${BPN}_${PV}_${PBT_ARCH}.tar.gz;name=${PBT_ARCH}"

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

