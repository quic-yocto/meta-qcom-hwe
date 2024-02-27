inherit autotools pkgconfig

DESCRIPTION = "Build BT HOST IPC"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/${LICENSE};md5=89aea4e17d99a7cacdbeed46a0096b10"

DEPENDS = "libchrome glib-2.0 fluoride"

SRC_URI += "git://git.codelinaro.org/clo/le/platform/vendor/qcom-opensource/bluetooth.git;protocol=https;rev=390a2ab14e80b5258fc9706f198dd387e72b6070;branch=bt-performant.qclinux.1.0.r1-rel;subdir=bt_audio \
            git://git.codelinaro.org/clo/le/platform/vendor/qcom-opensource/system/bt.git;protocol=https;rev=9664854e14d51aac133b793dac110bdaf26d82c5;branch=bt-performant.qclinux.1.0.r1-rel;subdir=stack/system/bt \
           "

S = "${WORKDIR}"

EXTRA_OEMAKE += 'BT_SOURCE=${S}'

EXTRA_OECONF = "--with-glib"
SOLIBS = ".so"

PACKAGE_ARCH = "${MACHINE_ARCH}"
FILES_SOLIBSDEV = ""
AUTOTOOLS_SCRIPT_PATH = "${S}/bt_audio/bthost_ipc"
