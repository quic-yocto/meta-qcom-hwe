inherit autotools pkgconfig

DESCRIPTION = "Build BT HOST IPC"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/${LICENSE};md5=89aea4e17d99a7cacdbeed46a0096b10"

DEPENDS = "libchrome glib-2.0 fluoride"

SRC_URI = "git://git.codelinaro.org/clo/le/platform/vendor/qcom-opensource/bluetooth.git;protocol=https;rev=feac37f59b09e012753bb7f4f48121619d227f2b;branch=bt-performant.qclinux.1.0.r1-rel;destsuffix=bluetooth/bt_audio \
           git://git.codelinaro.org/clo/le/platform/vendor/qcom-opensource/system/bt.git;protocol=https;rev=09b2d0f5ff746b9d0e7c79793af52ea5a0ecee44;branch=bt-performant.qclinux.1.0.r1-rel;destsuffix=bluetooth/stack/system/bt \
           git://git.codelinaro.org/clo/le/platform/vendor/qcom-opensource/system/bt.git;protocol=https;rev=09b2d0f5ff746b9d0e7c79793af52ea5a0ecee44;branch=bt-performant.qclinux.1.0.r1-rel;destsuffix=bluetooth/stack/system/btaudio_a2dp_hw/include"

BT_SOURCE = "${WORKDIR}/bluetooth"
S = "${BT_SOURCE}/bt_audio/bthost_ipc"

EXTRA_OEMAKE += 'BT_SOURCE=${BT_SOURCE}'

EXTRA_OECONF = "--with-glib"
SOLIBS = ".so"

PACKAGE_ARCH = "${MACHINE_ARCH}"
FILES_SOLIBSDEV = ""
