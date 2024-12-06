inherit autotools pkgconfig systemd

DESCRIPTION = "display Library"
LICENSE = "BSD-3-Clause & BSD-3-Clause-Clear"
BSD-3-Clause_LICENSE  = "file://sdm/include/core/display_interface.h;beginline=2;endline=22"
BSD-3-Clause-Clear_LICENSE = "file://sdm/include/core/display_interface.h;beginline=28;endline=29"

LIC_FILES_CHKSUM = " \
    ${BSD-3-Clause-Clear_LICENSE};md5=05a078dc8c6f02f6f67fa9078a5e2a3c \
    ${BSD-3-Clause_LICENSE};md5=ef93dc3f1e145b6c1f89b90a5230ef8a \
"

PACKAGE_ARCH = "${SOC_ARCH}"

SRCPROJECT = "git://git.codelinaro.org/clo/le/platform/hardware/qcom/display.git;protocol=https"
SRCBRANCH  = "display.qclinux.1.0.r1-rel"
SRCREV     = "f8fd6ee4a1a305da49a8c3c613b81be890cba7d3"

SRC_URI = "${SRCPROJECT};branch=${SRCBRANCH};destsuffix=display/hardware/qcom/display"

S = "${WORKDIR}/display/hardware/qcom/display"

EXTRA_OECONF += " --with-sanitized-headers=${STAGING_INCDIR}/linux-kernel-qcom/usr/include"
EXTRA_OECONF += " --enable-displayle"

PACKAGECONFIG ?= " \
                 ${@bb.utils.contains('COMBINED_FEATURES', 'drm', 'drm', '', d)} \
                 "

PACKAGECONFIG[drm] = "--enable-sdmhaldrm, --disable-sdmhaldrm, libdrm, libdrm"

DEPENDS += " grpc grpc-native protobuf protobuf-native "
DEPENDS += "libdrm \
            gbm \
            linux-kernel-qcom-headers \
            "

DEPENDS:append:qcm6490 = " qcom-displaydlkm"

QDCM_JSON = "qdcm_calib_data_nt36672e_lcd_video_mode_dsi_novatek_panel_with_DSC.json"

# Install path for qdcm calib files and lib config files
do_install:append() {
  install -d ${D}/usr/data/display
  install -m 0644 ${S}/config/snapdragon_color_libs_config.xml \
-D ${D}/usr/data/display/snapdragon_color_libs_config.xml
  install -m 0644 ${S}/config/clstc_config_library.xml \
-D ${D}/usr/data/display/clstc_config_library.xml
  install -m 0644 ${S}/config/${QDCM_JSON} -D ${D}/usr/data/display/${QDCM_JSON}
}

PACKAGES = "${PN}-dbg ${PN}-dev ${PN}"
FILES:${PN}  += " /usr/data/display/* "
FILES:${PN}  += " ${libdir}/* "
FILES:${PN}-dev  = " ${includedir}/* "
FILES:${PN}-dbg  = " ${libdir}/.debug/* "
