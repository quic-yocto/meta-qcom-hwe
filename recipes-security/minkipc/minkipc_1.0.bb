inherit qprebuilt pkgconfig

LICENSE          = "Qualcomm-Technologies-Inc.-Proprietary"
LIC_FILES_CHKSUM = "file://${QCOM_COMMON_LICENSE_DIR}${LICENSE};md5=58d50a3d36f27f1a1e6089308a49b403"

DESCRIPTION = "Libraries enabling MinkIPC"

DEPENDS += "glib-2.0 glibc linux-kernel-qcom-headers libdmabufheap securemsm-headers"

SRC_URI[qcm6490.sha256sum] = "4d4a33ece9dff14841fe56f8a9ef78da7b70d3df6c46aa610119e652044fbaf9"
SRC_URI[qcs9100.sha256sum] = "a42e32dcee9512f89bef5204cc4fb9f44fac029b35f86fcc704bc7dfa81b740a"
SRC_URI[qcs8300.sha256sum] = "eb92c73f25b3aebc76fda2c3b9b0151b57d6da2bb04d7212d05286f7027c52da"

SRC_URI = "https://${PBT_ARTIFACTORY}/${PBT_BUILD_ID}/${PBT_BIN_PATH}/${BPN}_${PV}_${PBT_ARCH}.tar.gz;name=${PBT_ARCH}"

FILES:${PN} += "/usr/bin/*"
FILES:${PN} += "${bindir}/*"
FILES:${PN} += "${libdir} ${includedir}"
FILES:${PN}-dev = "${libdir}/*.la"


INSANE_SKIP:${PN} = "dev-so"
INSANE_SKIP:${PN} += "debug-files"

