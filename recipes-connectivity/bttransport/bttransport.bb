inherit autotools-brokensep pkgconfig

LICENSE          = "Qualcomm-Technologies-Inc.-Proprietary"
LIC_FILES_CHKSUM = "file://${QCOM_COMMON_LICENSE_DIR}/${LICENSE};md5=58d50a3d36f27f1a1e6089308a49b403"


DESCRIPTION = "Bluetooth Vendor Transport"

DEPENDS = "glib-2.0 hci-qcomm-init"

SRC_URI += "git://qpm-git.qualcomm.com/home2/git/revision-history/platform/vendor/qcom-proprietary/bluetooth.git.git;protocol=https;rev=c199b2edf1952a825a4d367b4877e0736b76befc;branch=1020-bt-performant.qclinux.1.0.r1-rel;destsuffix=bluetooth/proprietary/bluetooth_transport"

S = "${WORKDIR}/bluetooth/proprietary/bluetooth_transport/hidl_transport"

FILES_SOLIBSDEV = ""
FILES:${PN} += "${libdir}"
INSANE_SKIP:${PN} = "dev-so"

SECURITY_CFLAGS = "${SECURITY_NO_PIE_CFLAGS}"

EXTRA_OECONF = "--with-lib-path=${STAGING_LIBDIR} \
                --with-common-includes=${STAGING_INCDIR} \
                --with-glib \
                --enable-static=yes \
               "

CPPFLAGS:append = " ${@bb.utils.contains('VARIANT', 'debug', '-DUSER_DEBUG', '', d)}"
CPPFLAGS:append = " ${@bb.utils.contains('VARIANT', 'debug', '-g', '', d)}"

CFLAGS:append = " -DUSE_ANDROID_LOGGING "
CPPFLAGS:append = " -DUSE_ANDROID_LOGGING "
