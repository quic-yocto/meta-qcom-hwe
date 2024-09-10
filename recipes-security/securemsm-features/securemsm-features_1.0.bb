inherit qprebuilt pkgconfig

LICENSE          = "Qualcomm-Technologies-Inc.-Proprietary"
LIC_FILES_CHKSUM = "file://${QCOM_COMMON_LICENSE_DIR}${LICENSE};md5=58d50a3d36f27f1a1e6089308a49b403"

DESCRIPTION = "securemsm-features with QseecomAPI user space library to interact with qseecom driver"

DEPENDS += "libxml2 libtinyxml2 linux-kernel-qcom-headers glib-2.0 glibc libdmabufheap securemsm-headers minkipc property-vault jsoncpp qmi-framework curl"

SRC_URI[qcm6490.sha256sum] = "8831a505a51ca5fc83188ff44ae61b2ae41fad91ac002a7ce697838f3e4a8475"
SRC_URI[qcs9100.sha256sum] = "c4ea5d61d70438e2b010170ebd27f45a217403b25594a253c28fa768f5880995"
SRC_URI[qcs8300.sha256sum] = "cafcc0b27035d05133c0f6c6cd5754e9c25bceacee4cd8266ba4cf0ed18eebb1"

SRC_URI = "https://${PBT_ARTIFACTORY}/${PBT_BUILD_ID}/${PBT_BIN_PATH}/${BPN}_${PV}_${PBT_ARCH}.tar.gz;name=${PBT_ARCH}"

FILES:${PN} += "/usr/bin/*"
FILES:${PN} += "/usr/bin/"
FILES:${PN} += "${bindir}/*"
FILES:${PN} += "${libdir} ${includedir}"
FILES:${PN}-dev = "${libdir}/*.la"


INSANE_SKIP:${PN} = "dev-so"
INSANE_SKIP:${PN} += "dev-deps"
INSANE_SKIP:${PN} += "debug-files"
INSANE_SKIP:${PN} += "file-rdeps"

