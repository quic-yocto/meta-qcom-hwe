# SPDX-License-Identifier: BSD-3-Clause-Clear

DTBBIN_DEPLOYDIR = "${WORKDIR}/qcom_dtbbin_deploy-${PN}"
DTBBIN_SIZE ?= "4096"

do_qcom_dtbbin_deploy[depends] += "dosfstools-native:do_populate_sysroot mtools-native:do_populate_sysroot"
do_qcom_dtbbin_deploy[cleandirs] = "${DTBBIN_DEPLOYDIR}"
do_qcom_dtbbin_deploy() {
    for dtbf in ${KERNEL_DEVICETREE}; do
        bbdebug 1 " combining: $dtbf"
        dtb=`normalize_dtb "$dtbf"`
        dtb_ext=${dtb##*.}
        dtb_base_name=`basename $dtb .$dtb_ext`
        mkdir -p ${DTBBIN_DEPLOYDIR}/$dtb_base_name
        cp ${D}/${KERNEL_DTBDEST}/$dtb_base_name.dtb ${DTBBIN_DEPLOYDIR}/$dtb_base_name/combined-dtb.dtb
        mkfs.vfat $@ -C ${DTBBIN_DEPLOYDIR}/dtb-${dtb_base_name}-image.vfat ${DTBBIN_SIZE}
        mcopy -i "${DTBBIN_DEPLOYDIR}/dtb-${dtb_base_name}-image.vfat" -vsmpQ ${DTBBIN_DEPLOYDIR}/$dtb_base_name/* ::/
        rm -rf ${DTBBIN_DEPLOYDIR}/$dtb_base_name
    done
}
addtask qcom_dtbbin_deploy after do_populate_sysroot do_packagedata before do_deploy

# Setup sstate, see deploy.bbclass
SSTATETASKS += "do_qcom_dtbbin_deploy"
do_qcom_dtbbin_deploy[sstate-inputdirs] = "${DTBBIN_DEPLOYDIR}"
do_qcom_dtbbin_deploy[sstate-outputdirs] = "${DEPLOY_DIR_IMAGE}"

python do_qcom_dtbbin_deploy_setscene () {
    sstate_setscene(d)
}
addtask do_qcom_dtbbin_deploy_setscene

do_qcom_dtbbin_deploy[stamp-extra-info] = "${MACHINE_ARCH}"
