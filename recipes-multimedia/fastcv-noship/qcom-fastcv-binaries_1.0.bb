inherit qprebuilt pkgconfig

LICENSE          = "Qualcomm-Technologies-Inc.-Proprietary"
LIC_FILES_CHKSUM = "file://${QCOM_COMMON_LICENSE_DIR}${LICENSE};md5=58d50a3d36f27f1a1e6089308a49b403"

DESCRIPTION = "Generates libfastcvopt"

DEPENDS += "libdmabufheap syslog-plumber property-vault glib-2.0 fastrpc"

RDEPENDS:${PN} += "${PN}-cpu ${PN}-dsp"


SRC_URI[qcm6490.sha256sum] = "9ccc9bfc99372f81da44504ba6c7115681b0813b301280723df12c4c2f628495"
SRC_URI[qcs9100.sha256sum] = "5c39f41f4f77e4f2637d11e13073c7e27bd38875108f84bf1fa88036f33ec95b"
SRC_URI[qcs8300.sha256sum] = "8d57596528d04e7df89ccf0aae258346a646fdec78bed8318fe6a6f1095071c1"

SRC_URI = "https://${PBT_ARTIFACTORY}/${PBT_BUILD_ID}/${PBT_BIN_PATH}/${BPN}_${PV}_${PBT_ARCH}.tar.gz;name=${PBT_ARCH}"

FILES:${PN}-cpu = "${libdir}/libfastcvopt.so"
FILES:${PN}-cpu += "/usr/include/*"
FILES:${PN}-dsp += "${libdir}/rfsa/adsp/* ${libdir}/libfastcvdsp_stub.so"


INSANE_SKIP:${PN} = "already-stripped"
INSANE_SKIP:${PN}-dsp = "arch"


SOLIBS = ".so"
FILES_SOLIBSDEV = ""

ALLOW_EMPTY:${PN} = "1"

PACKAGES =+ "${PN}-cpu ${PN}-dsp"
