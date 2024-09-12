SUMMARY = "Generate and place partition artifacts in DEPLOYDIR"

LICENSE = "BSD-3-Clause-Clear"
LIC_FILES_CHKSUM = " file://${QCOM_COMMON_LICENSE_DIR}/${LICENSE};md5=3771d4920bd6cdb8cbdf1e8344489ee0"

DEPENDS += "qcom-ptool-native"

inherit python3native

do_configure[noexec] = "1"

do_compile[depends] += " \
    virtual/partconf:do_deploy \
   "
do_compile() {
    # ptool to generate partition bins
    ${PYTHON} ${STAGING_BINDIR_NATIVE}/ptool.py -x ${DEPLOY_DIR_IMAGE}/partition.xml
}

inherit deploy
do_deploy() {
    install -m 0644 ${B}/gpt_backup*.bin -D ${DEPLOYDIR}
    install -m 0644 ${B}/gpt_both*.bin -D ${DEPLOYDIR}
    install -m 0644 ${B}/gpt_empty*.bin -D ${DEPLOYDIR}
    install -m 0644 ${B}/gpt_main*.bin -D ${DEPLOYDIR}
    install -m 0644 ${B}/patch*.xml -D ${DEPLOYDIR}
    install -m 0644 ${B}/rawprogram*.xml -D ${DEPLOYDIR}
    install -m 0644 ${B}/zeros_*.bin -D ${DEPLOYDIR}
    install -m 0644 ${B}/wipe_rawprogram_PHY*.xml -D ${DEPLOYDIR}
}
addtask deploy before do_build after do_install

PACKAGE_ARCH = "${SOC_ARCH}"
