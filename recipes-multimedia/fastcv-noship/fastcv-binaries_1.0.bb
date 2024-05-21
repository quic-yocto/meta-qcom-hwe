inherit qprebuilt pkgconfig

LICENSE          = "Qualcomm-Technologies-Inc.-Proprietary"
LIC_FILES_CHKSUM = "file://${QCOM_COMMON_LICENSE_DIR}${LICENSE};md5=58d50a3d36f27f1a1e6089308a49b403"

DESCRIPTION = "Generates libfastcvopt"

DEPENDS += "libdmabufheap syslog-plumber property-vault glib-2.0 fastrpc"

RDEPENDS:${PN} += "${PN}-cpu ${PN}-dsp"

SRC_URI[sha256sum] = "37523ceded3266c5c884633b3e5d1015d32b87202cbe8093b8fc819407f17361"


SRC_URI = "https://${PBT_ARTIFACTORY}/${PBT_BUILD_ID}/${PBT_BIN_PATH}/${BPN}_${PV}_${PBT_ARCH}.tar.gz"




PACKAGE_ARCH = "${MACHINE_ARCH}"

FILES:${PN}-cpu = "${libdir}/libfastcvopt.so"
FILES:${PN}-cpu += "/usr/include/*"
FILES:${PN}-dsp += "${libdir}/rfsa/adsp/* ${libdir}/libfastcvdsp_stub.so"


INSANE_SKIP:${PN} = "already-stripped"
INSANE_SKIP:${PN}-dsp = "arch"


SOLIBS = ".so"
FILES_SOLIBSDEV = ""

ALLOW_EMPTY:${PN} = "1"

PACKAGES =+ "${PN}-cpu ${PN}-dsp"
