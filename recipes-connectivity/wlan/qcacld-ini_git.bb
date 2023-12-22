DESCRIPTION = "QCOM WLAN ini file"
LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/${LICENSE};md5=550794465ba0ec5312d6919e203a55f9"

SRC_URI = "file://WCNSS_qcom_cfg.ini"
S = "${WORKDIR}"

do_install () {
	install -d ${D}/lib/firmware/wlan/qca_cld
	install -m 0644 ${B}/WCNSS_qcom_cfg.ini ${D}/lib/firmware/wlan/qca_cld/WCNSS_qcom_cfg.ini
}

FILES:${PN} += "/lib/firmware/wlan/qca_cld/*"
