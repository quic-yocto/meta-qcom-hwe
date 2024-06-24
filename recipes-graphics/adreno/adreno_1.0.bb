inherit qprebuilt pkgconfig

LICENSE          = "Qualcomm-Technologies-Inc.-Proprietary"
LIC_FILES_CHKSUM = "file://${QCOM_COMMON_LICENSE_DIR}${LICENSE};md5=58d50a3d36f27f1a1e6089308a49b403"

DESCRIPTION = "Adreno Graphics"

DEPENDS += "wayland glib-2.0 linux-kernel-qcom-headers property-vault libdmabufheap gbm"

PROVIDES = "  virtual/libgles1 virtual/libgles2 virtual/egl"

SRC_URI[qcm6490.sha256sum] = "efa9b263faef4e0891b2bc568f9ca18bab1c0e810311c764f0c59ebb5fb1ee39"
SRC_URI[qcs9100.sha256sum] = "6b4d030a4abe4b4ee12a93ae446850e8541dfbc2d1e0ad53dc8f61ad2ff67a55"

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
