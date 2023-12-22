inherit autotools-brokensep pkgconfig

HOMEPAGE         = "http://support.cdmatech.com"
LICENSE          = "Qualcomm-Technologies-Inc.-Proprietary"
LIC_FILES_CHKSUM = "file://${QCOM_COMMON_LICENSE_DIR}/${LICENSE};md5=58d50a3d36f27f1a1e6089308a49b403"


DESCRIPTION = "Bluetooth Vendor Transport"
PR = "r0"
DEPENDS = "glib-2.0 hci-qcomm-init"

#RDEPENDS:${PN} = "libcutils"

SRC_URI   = "file://bluetooth/proprietary/bluetooth_transport/hidl_transport"

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

CFLAGS:append = " -DUSE_ANDROID_LOGGING "
CPPFLAGS:append = " -DUSE_ANDROID_LOGGING "

