inherit qprebuilt pkgconfig

LICENSE          = "Qualcomm-Technologies-Inc.-Proprietary"
LIC_FILES_CHKSUM = "file://${QCOM_COMMON_LICENSE_DIR}${LICENSE};md5=58d50a3d36f27f1a1e6089308a49b403"

DESCRIPTION = "libsdmextension Library"

DEPENDS += "display-hal-linux jsoncpp glib-2.0 display-color-linux property-vault libdrm openssl linux-kernel-qcom-headers"

SRC_URI[qcm6490.sha256sum] = "e31ac9493e0bedca9fd4cb88000de8a4652ba1d9b3d168a66a35c2931f3ddc0d"
SRC_URI[qcs9100.sha256sum] = "ad5fa5f2aa88915d3aae7a5a765a069c8d95e62cef9946d549fea12bbe63c255"

SRC_URI = "https://${PBT_ARTIFACTORY}/${PBT_BUILD_ID}/${PBT_BIN_PATH}/${BPN}_${PV}_${PBT_ARCH}.tar.gz;name=${PBT_ARCH}"

PACKAGE_ARCH = "${MACHINE_ARCH}"

FILES:${PN}  += " /usr/data/display/* "
FILES:${PN}  += " ${libdir}/* "
FILES:${PN}-dev  = " ${includedir}/* "
FILES:${PN}-dbg  = " ${libdir}/.debug/* "


PACKAGES = "${PN}-dbg ${PN}-dev ${PN}"
