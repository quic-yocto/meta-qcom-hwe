inherit autotools pkgconfig systemd

HOMEPAGE         = "http://support.cdmatech.com"
LICENSE          = "Qualcomm-Technologies-Inc.-Proprietary"
LIC_FILES_CHKSUM = "file://${QCOM_COMMON_LICENSE_DIR}/${LICENSE};md5=58d50a3d36f27f1a1e6089308a49b403"


DESCRIPTION = "Bluetooth BT Vendor Filters"
DEPENDS += "glib-2.0"
DEPENDS += "libchrome"

#RDEPENDS:${PN} = "libcutils"

SRC_DIR   = "${WORKSPACE}//bluetooth/proprietary/hci_qcomm_init/"
FILESPATH =+ "${WORKSPACE}/:"
SRC_URI   = "file://bluetooth/proprietary/hci_qcomm_init/ \
            file://start_btnvtool.sh \
            file://btnvtool.service \
            "

S = "${WORKDIR}/bluetooth/proprietary/hci_qcomm_init"

export WORKSPACE

BASEPRODUCT = "${@d.getVar('PRODUCT', False)}"

EXTRA_OECONF = "--with-lib-path=${STAGING_LIBDIR} \
                --with-glib \
                --enable-target=${BASEMACHINE} \
                --enable-rome=${BASEPRODUCT} \
                --with-common-includes=${STAGING_INCDIR} \
               "

CFLAGS:append = " -DUSE_ANDROID_LOGGING "
#LDFLAGS:append = " -llog "

do_install:append () {
    install -d ${D}${systemd_system_unitdir}
    install -m 0644 ${WORKDIR}/btnvtool.service ${D}${systemd_system_unitdir}/btnvtool.service
    install -m 0744 ${WORKDIR}/start_btnvtool.sh  -D ${D}${sysconfdir}/initscripts/start_btnvtool.sh

    if [ -d "${SRC_DIR}" ]; then
        install -d ${D}${includedir}
        install -m 644 ${S}/bt_nv.h ${D}${includedir}
        install -m 644 ${S}/btqsocnvm.h ${D}${includedir}
        install -m 644 ${S}/btqsocnvmutils.h ${D}${includedir}
    fi
}

SYSTEMD_SERVICE:${PN} = "btnvtool.service"
