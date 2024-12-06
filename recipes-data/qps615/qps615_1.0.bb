inherit module systemd

DESCRIPTION = "TOSHIBA QPS615 driver"
LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/files/common-licenses/${LICENSE};md5=801f80980d171dd6425610833a22dbe6"

SRCPROJECT = "git://git.codelinaro.org/clo/le/platform/vendor/opensource/data-eth.git;protocol=https"
SRCBRANCH  = "data-kernel.qclinux.1.0.r1-rel"
SRCREV     = "5f1f52ca95a7d1ea0ab83ea65f7381c8bc8a863e"

SRC_URI = "${SRCPROJECT};branch=${SRCBRANCH};destsuffix=data-eth \
           file://qps615.service"

S = "${WORKDIR}/data-eth/drivers/qps615/src"
MAKE_TARGETS = "modules"

MODULE_NAME = "tc956x_pcie_eth"
EXTRA_OEMAKE += " MACHINE='${MACHINE}'"
RPROVIDES:${PN} += "kernel-module-qps615"

RRECOMMENDS:${PN} += "qps615-firmware"

do_install:append () {
	install -d ${D}${systemd_unitdir}/system/
	install -d ${D}${systemd_unitdir}/system/multi-user.target.wants/

	install -m 0644 ${WORKDIR}/qps615.service -D ${D}${systemd_unitdir}/system/qps615.service
	ln -sf -r ${D}${systemd_unitdir}/system/qps615.service \
		${D}${systemd_unitdir}/system/multi-user.target.wants/qps615.service
}

FILES:${PN}+="${systemd_unitdir}/system/qps615.service"
FILES:${PN}+="${systemd_unitdir}/system/multi-user.target.wants/qps615.service"
