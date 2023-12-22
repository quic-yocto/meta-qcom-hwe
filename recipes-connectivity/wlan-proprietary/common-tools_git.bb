inherit autotools-brokensep pkgconfig

HOMEPAGE         = "http://support.cdmatech.com"
DESCRIPTION = "Qualcomm Atheros common tools"
LICENSE          = "Qualcomm-Technologies-Inc.-Proprietary"
LIC_FILES_CHKSUM = "file://${QCOM_COMMON_LICENSE_DIR}${LICENSE};md5=58d50a3d36f27f1a1e6089308a49b403"

DEPENDS += "diag libnl glib-2.0"

DEPENDS:append = " cld80211-lib"

PR = "r2"
PV = "1.0"

PACKAGE_ARCH = "${MACHINE_ARCH}"

CFLAGS += "-I ${STAGING_INCDIR}/libnl3"

SRC_URI = ""

EXTRA_OECONF = "--with-glib"
EXTRA_OECONF:append:qcm6490 = "--enable-cld80211 cld80211_lib_path=${STAGING_INCDIR}/cld80211-lib"

do_install () {
		install -d ${D}${sbindir}
		install -m 755 ${S}/cnssdiag/cnss_diag ${D}${sbindir}
			if [ ${BASEMACHINE} == "qrb5165" ]; then
				sed -i 's/cnss_diag -f/cnss_diag -z \/firmware\/image -f/' ${WORKDIR}/cnss_diag.service
			fi
			install -m 0644 ${WORKDIR}/cnss_diag.service -D ${D}/lib/systemd/system/cnss_diag.service
		install -d ${D}/lib/systemd/system/multi-user.target.wants/
		ln -sf /lib/systemd/system/cnss_diag.service \
			${D}/lib/systemd/system/multi-user.target.wants/cnss_diag.service
}

do_install:append:() {
		install -m 755 ${S}/ctrl_app_dut/ctrl_app_dut ${D}${sbindir}
}

FILES:${PN} += "/lib/systemd/system/cnss_diag.service"
FILES:${PN} += "/lib/systemd/system/multi-user.target.wants/cnss_diag.service"
