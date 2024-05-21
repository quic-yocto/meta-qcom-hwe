inherit qprebuilt pkgconfig

LICENSE          = "Qualcomm-Technologies-Inc.-Proprietary"
LIC_FILES_CHKSUM = "file://${QCOM_COMMON_LICENSE_DIR}${LICENSE};md5=58d50a3d36f27f1a1e6089308a49b403"

DESCRIPTION = "AROSP."

DEPENDS += "glib-2.0 virtual/kernel diag diag-router"

RDEPENDS:${PN} = "glib-2.0 diag diag-router"

PBT_ARCH = "armv8-2a"

SRC_URI[sha256sum] = "f637f779917e04dd5fe4f94221b2e592a7807f752b3397ec88255179f632e69b"

SRC_URI = "https://${PBT_ARTIFACTORY}/${PBT_BUILD_ID}/${PBT_BIN_PATH}/${BPN}_${PV}_${PBT_ARCH}.tar.gz"

SOLIBS = ".so"
FILES_SOLIBSDEV = ""
