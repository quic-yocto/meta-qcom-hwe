inherit qprebuilt pkgconfig

LICENSE          = "Qualcomm-Technologies-Inc.-Proprietary"
LIC_FILES_CHKSUM = "file://${QCOM_COMMON_LICENSE_DIR}${LICENSE};md5=58d50a3d36f27f1a1e6089308a49b403"

DESCRIPTION = "Libraries enabling MinkIPC"

DEPENDS += "glib-2.0 glibc linux-kernel-qcom-headers libdmabufheap securemsm-headers"

SRC_URI[qcm6490.sha256sum] = "cc2c79e4f056c6a266c48dea21e71b3e20bbd250acead1b8c78273312b0d523c"
SRC_URI[qcs9100.sha256sum] = "924e20e38028b6b393e7df0d05e71682aa5a15e653ba04a787c8d2f8ce76227c"
SRC_URI[qcs8300.sha256sum] = "147cb86c4f8a92bcf7ac2f976800e4b1076f18cc109da23739ad49093d485e2b"

SRC_URI = "https://${PBT_ARTIFACTORY}/${PBT_BUILD_ID}/${PBT_BIN_PATH}/${BPN}_${PV}_${PBT_ARCH}.tar.gz;name=${PBT_ARCH}"

FILES:${PN} += "/usr/bin/*"
FILES:${PN} += "${bindir}/*"
FILES:${PN} += "${libdir} ${includedir}"
FILES:${PN}-dev = "${libdir}/*.la"


INSANE_SKIP:${PN} = "dev-so"
INSANE_SKIP:${PN} += "debug-files"

