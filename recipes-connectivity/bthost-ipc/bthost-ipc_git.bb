inherit autotools pkgconfig

DESCRIPTION = "Build BT HOST IPC"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/${LICENSE};md5=89aea4e17d99a7cacdbeed46a0096b10"

DEPENDS = "libchrome glib-2.0 fluoride"

QCOM_BLUETOOTH_SRC ?= "git://git.codelinaro.org/clo/le/platform/vendor/qcom-opensource/bluetooth.git;protocol=https"
QCOM_BLUETOOTH_SRCBRANCH ?= "bt-performant.qclinux.1.0.r1-rel"
QCOM_BLUETOOTH_SRCREV ?= "1710c237b493454dc93f41de09b50cd8d109f970"

QCOM_SYSTEM_BT_SRC ?= "git://git.codelinaro.org/clo/le/platform/vendor/qcom-opensource/system/bt.git;protocol=https"
QCOM_SYSTEM_BT_SRCBRANCH ?= "bt-performant.qclinux.1.0.r1-rel"
QCOM_SYSTEM_BT_SRCDEV ?= "677327d9157df86311d8be4a3fd60fb2d5169ad2"

SRC_URI = "${QCOM_BLUETOOTH_SRC};branch=${QCOM_BLUETOOTH_SRCBRANCH};rev=${QCOM_BLUETOOTH_SRCREV};destsuffix=bluetooth/bt_audio \
           ${QCOM_SYSTEM_BT_SRC};branch=${QCOM_SYSTEM_BT_SRCBRANCH};rev=${QCOM_SYSTEM_BT_SRCDEV};destsuffix=bluetooth/stack/system/bt \
           ${QCOM_SYSTEM_BT_SRC};branch=${QCOM_SYSTEM_BT_SRCBRANCH};rev=${QCOM_SYSTEM_BT_SRCDEV};destsuffix=bluetooth/stack/system/btaudio_a2dp_hw/include"

BT_SOURCE = "${WORKDIR}/bluetooth"

S = "${BT_SOURCE}/bt_audio/bthost_ipc"

EXTRA_OEMAKE += 'BT_SOURCE=${BT_SOURCE}'

EXTRA_OECONF = "--with-glib"

SOLIBS = ".so"

PACKAGE_ARCH = "${MACHINE_ARCH}"

FILES_SOLIBSDEV = ""
INSANE_SKIP:${PN} = "dev-so"

FILES:${PN} += "/usr/lib/libbthost_if.so.* /usr/lib/libbthost_if_sink.so.* /usr/lib/libbthost_if*.so"

do_install:append() {
        cd  ${D}/${libdir}/ && ln -sf libbthost_if.so.1.0.0 libbthost_if.so
        cd  ${D}/${libdir}/ && ln -sf libbthost_if_sink.so.1.0.0 libbthost_if_sink.so
}
