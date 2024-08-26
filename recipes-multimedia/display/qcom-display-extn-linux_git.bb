inherit qprebuilt pkgconfig

LICENSE          = "Qualcomm-Technologies-Inc.-Proprietary"
LIC_FILES_CHKSUM = "file://${QCOM_COMMON_LICENSE_DIR}${LICENSE};md5=58d50a3d36f27f1a1e6089308a49b403"

DESCRIPTION = "libsdmextension Library"

DEPENDS += "qcom-display-hal-linux jsoncpp glib-2.0 qcom-display-color-linux property-vault libdrm openssl linux-kernel-qcom-headers syslog-plumber"

QCM6490_CHKSUM = "917ce9f1c2b4019108ea21b1b8a904b5ed6a98b2728feba9ac2f9ba78c58bd55"

SRC_URI[qcm6490.sha256sum] = "${QCM6490_CHKSUM}"

SRC_URI = "https://${PBT_ARTIFACTORY}/${PBT_BUILD_ID}/${PBT_BIN_PATH}/${BPN}_${PV}_${PBT_ARCH}.tar.gz;name=${PBT_ARCH}"

FILES:${PN}  += " /usr/data/display/* "
FILES:${PN}  += " /data/vendor/display "
FILES:${PN}  += " ${libdir}/* "
FILES:${PN}-dev  = " ${includedir}/* "
FILES:${PN}-dbg  = " ${libdir}/.debug/* "


PACKAGES = "${PN}-dbg ${PN}-dev ${PN}"
