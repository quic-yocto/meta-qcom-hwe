inherit autotools pkgconfig

DESCRIPTION = "Bluetooth OBEX"
LICENSE = "BSD-3-Clause"
HOMEPAGE = "https://www.codeaurora.org/"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/files/common-licenses/\
${LICENSE};md5=550794465ba0ec5312d6919e203a55f9"

DEPENDS += "glib-2.0 btvendorhal"

SRC_URI = "git://git.codelinaro.org/clo/le/platform/qcom-opensource/bt.git;protocol=http;rev=50f31639122c7496e0e13989c0ee97fd4ea5ac1f;branch=bt-performant.qclinux.1.0.r1-rel;subdir=btapp \
           "
S = "${WORKDIR}"

EXTRA_OEMAKE += 'BT_SOURCE=${S}'

AUTOTOOLS_SCRIPT_PATH = "${S}/btapp/obex_profiles"

EXTRA_OECONF = "--with-glib"
EXTRA_OECONF += "--with-common-includes=${STAGING_INCDIR}"
