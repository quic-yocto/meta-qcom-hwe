inherit autotools pkgconfig systemd

LICENSE          = "Qualcomm-Technologies-Inc.-Proprietary"
LIC_FILES_CHKSUM = "file://${QCOM_COMMON_LICENSE_DIR}/${LICENSE};md5=58d50a3d36f27f1a1e6089308a49b403"

DESCRIPTION = "Bluetooth BT Vendor Filters"
DEPENDS += "glib-2.0"
DEPENDS += "libchrome"

SRC_URI += "git://qpm-git.qualcomm.com/home2/git/revision-history/platform/vendor/qcom-proprietary/ship/bt/hci_qcomm_init.git.git;protocol=https;rev=d6a97c4783e10014f69d89e0d6e9fda14038776c;branch=1020-bt-performant.qclinux.1.0.r1-rel;destsuffix=bluetooth/proprietary/hci_qcomm_init \
           file://start_btnvtool.sh \
           file://btnvtool.service \
           "

S = "${WORKDIR}/bluetooth/proprietary/hci_qcomm_init"

EXTRA_OECONF = "--with-lib-path=${STAGING_LIBDIR} \
                --with-glib \
                --with-common-includes=${STAGING_INCDIR} \
               "

CFLAGS:append = " -DUSE_ANDROID_LOGGING "

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
