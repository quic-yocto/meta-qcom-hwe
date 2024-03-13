inherit autotools pkgconfig

DESCRIPTION = "Build BT HOST IPC"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/${LICENSE};md5=89aea4e17d99a7cacdbeed46a0096b10"

DEPENDS = "libchrome glib-2.0 fluoride"

SRC_URI += "git://git.codelinaro.org/clo/le/platform/vendor/qcom-opensource/bluetooth.git;protocol=https;rev=808a5314f33e9cb1507c0c0db102d82c15ee6f8f;branch=bt-performant.qclinux.1.0.r1-rel;destsuffix=bluetooth/bt_audio \
           git://git.codelinaro.org/clo/le/platform/vendor/qcom-opensource/system/bt.git;protocol=https;rev=5c924afb77845b72591a41108f98ce91ae0c1373;branch=bt-performant.qclinux.1.0.r1-rel;destsuffix=bluetooth/stack/system/bt \
           "

BT_SOURCE = "${WORKDIR}/bluetooth"
S = "${BT_SOURCE}/bt_audio/bthost_ipc"

EXTRA_OEMAKE += 'BT_SOURCE=${BT_SOURCE}'

EXTRA_OECONF = "--with-glib"
SOLIBS = ".so"

PACKAGE_ARCH = "${MACHINE_ARCH}"
FILES_SOLIBSDEV = ""
