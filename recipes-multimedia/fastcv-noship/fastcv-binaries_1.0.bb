inherit qprebuilt pkgconfig

LICENSE          = "Qualcomm-Technologies-Inc.-Proprietary"
LIC_FILES_CHKSUM = "file://${QCOM_COMMON_LICENSE_DIR}${LICENSE};md5=58d50a3d36f27f1a1e6089308a49b403"

DESCRIPTION = "Generates libfastcvopt"

DEPENDS += "libdmabufheap syslog-plumber property-vault glib-2.0 fastrpc"

SRCREV = "87e9e28a5714ffa7cb291eb480b61b8ac6a59c49"

SRC_URI = "git://qpm-git.qualcomm.com/home2/git/revision-history/qualcomm_linux-spf-1-0-le-qclinux-1-0-r1_api-linux_history_prebuilts.git;protocol=https;branch=LE.QCLINUX.1.0.R1"

PREBUILT_TARBALL = "fastcv-binaries_1.0_qcm6490.tar.gz"

S = "${WORKDIR}/git/apps_proc/prebuilt_HY22"


PACKAGE_ARCH = "${MACHINE_ARCH}"

PACKAGES =+ "${PN}-cpu ${PN}-dsp"
FILES:${PN}-cpu = "${libdir}/libfastcvopt.so"
INSANE_SKIP:${PN}-dsp = "arch"
FILES:${PN}-dsp += "${libdir}/rfsa/adsp/* ${libdir}/libfastcvdsp_stub.so"

SOLIBS = ".so"
FILES_SOLIBSDEV = ""

RDEPENDS:${PN} += "${PN}-cpu ${PN}-dsp"
ALLOW_EMPTY:${PN} = "1"
