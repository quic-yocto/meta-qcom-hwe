PV:qcom-base-bsp = "2.11"

FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:"

LIC_FILES_CHKSUM:qcom-base-bsp = "file://COPYING;md5=5ebcb90236d1ad640558c3d3cd3035df \
                                  file://README;beginline=1;endline=56;md5=6e4b25e7d74bfc44a32ba37bdf5210a6 \
                                  file://wpa_supplicant/wpa_supplicant.c;beginline=1;endline=12;md5=f5ccd57ea91e04800edb88267bf8eae4"

SRC_URI:remove:qcom-base-bsp = "file://0001-PEAP-client-Update-Phase-2-authentication-requiremen.patch \
				file://CVE-2024-3596_00.patch \
				file://CVE-2024-3596_01.patch \
				file://CVE-2024-3596_02.patch \
				file://CVE-2024-3596_03.patch \
				file://CVE-2024-3596_04.patch \
				file://CVE-2024-3596_05.patch \
				file://CVE-2024-3596_06.patch \
				file://CVE-2024-3596_07.patch \
				file://CVE-2024-3596_08.patch \
				file://0001-SAE-Check-for-invalid-Rejected-Groups-element-length.patch \
				file://0002-SAE-Check-for-invalid-Rejected-Groups-element-length.patch \
				file://0003-SAE-Reject-invalid-Rejected-Groups-element-in-the-pa.patch \
"

SRC_URI:append:qcom-base-bsp = " file://defconfig.qcom \
				 file://0002-UPSTREAM-WNM-Extend-workaround-for-broken-AP-operating-class-behavior.patch \
"

SRC_URI[sha256sum] = "912ea06f74e30a8e36fbb68064d6cdff218d8d591db0fc5d75dee6c81ac7fc0a"

do_configure:append:qcom-base-bsp() {
	install -m 0644 ${WORKDIR}/defconfig.qcom wpa_supplicant/.config
}
