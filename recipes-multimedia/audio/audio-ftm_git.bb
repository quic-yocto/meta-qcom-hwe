inherit qprebuilt pkgconfig

LICENSE          = "Qualcomm-Technologies-Inc.-Proprietary"
LIC_FILES_CHKSUM = "file://${QCOM_COMMON_LICENSE_DIR}${LICENSE};md5=58d50a3d36f27f1a1e6089308a49b403"

DESCRIPTION = "Audio FTM"

DEPENDS += "tinyalsa glib-2.0 agm mm-audio-headers args"

PBT_ARCH = "armv8-2a"

SRC_URI[sha256sum] = "085cd276cc6d2de30435eb61486bf427ab09f9d774ea2e3d334c56f3f9c04f72"

SRC_URI = "https://${PBT_ARTIFACTORY}/${PBT_BUILD_ID}/${PBT_BIN_PATH}/${BPN}_${PV}_${PBT_ARCH}.tar.gz"

FILES:${PN}-dbg  = "${libdir}/.debug/* ${bindir}/.debug/*"
FILES:${PN}      = "${libdir}/*.so ${libdir}/*.so.* ${sysconfdir}/* ${bindir}/* ${libdir}/pkgconfig/*"
FILES:${PN}-dev  = "${libdir}/*.la ${includedir}"
