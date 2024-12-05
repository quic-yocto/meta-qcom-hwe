inherit qprebuilt pkgconfig

LICENSE          = "Qualcomm-Technologies-Inc.-Proprietary"
LIC_FILES_CHKSUM = "file://${QCOM_COMMON_LICENSE_DIR}${LICENSE};md5=58d50a3d36f27f1a1e6089308a49b403"

DESCRIPTION = "To create remote debug agent for LE"

PBT_ARCH = "aarch64"

AARCH64_SHA256SUM = "d84e34e2fc2f22a2e57e329e88397c677bbf8edee847a2c476b69e0b4b38595c"

SRC_URI[aarch64.sha256sum] = "${AARCH64_SHA256SUM}"

SRC_URI = "https://${PBT_ARTIFACTORY}/${PBT_BUILD_ID}/${PBT_BIN_PATH}/${BPN}_${PV}_${PBT_ARCH}.tar.gz;name=${PBT_ARCH}"

PACKAGECONFIG ?= "glib"
PACKAGECONFIG[glib] = "--with-glib, --without-glib, glib-2.0"

FILES:${PN}      = "${bindir}/*"
