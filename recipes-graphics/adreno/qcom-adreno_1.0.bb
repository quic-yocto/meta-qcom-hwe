inherit qprebuilt pkgconfig

LICENSE          = "Qualcomm-Technologies-Inc.-Proprietary"
LIC_FILES_CHKSUM = "file://${QCOM_COMMON_LICENSE_DIR}${LICENSE};md5=58d50a3d36f27f1a1e6089308a49b403"

DESCRIPTION = "Adreno Graphics"

DEPENDS += "wayland glib-2.0 linux-kernel-qcom-headers property-vault qcom-libdmabufheap gbm"

PROVIDES = "  virtual/libgles1 virtual/libgles2 virtual/egl"

QCM6490_SHA256SUM = "89269c36b5c179327d015724371e167e6a72cbdd640242c56f2b6f629618a44a"
QCS9100_SHA256SUM = "3709ab3ff2775209317c4a616c8926aa807c3d8058f7d41e83bb20e59e6277a8"
QCS8300_SHA256SUM = "7093fc00ae39d8c39c960507e5c8f05667b91fd5e46f46c05b991652b53a86da"

SRC_URI[qcm6490.sha256sum] = "${QCM6490_SHA256SUM}"
SRC_URI[qcs9100.sha256sum] = "${QCS9100_SHA256SUM}"
SRC_URI[qcs8300.sha256sum] = "${QCS8300_SHA256SUM}"

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

