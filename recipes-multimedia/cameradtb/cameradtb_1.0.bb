inherit module deploy

DESCRIPTION = "QCOM Camera device-tree"

LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/${LICENSE};md5=550794465ba0ec5312d6919e203a55f9"

SRC_URI     =  "git://git.codelinaro.org/clo/le/platform/vendor/opensource/camera-devicetree.git;protocol=https;rev=d4fd0e969f2dc0349412bf4414f0b2a1b681d2a7;branch=camera-kernel.qclinux.1.0.r1-rel;destsuffix=vendor/qcom/opensource/camera-devicetree"

DEPENDS:qcom-custom-bsp += "cameradlkm"

S = "${WORKDIR}/vendor/qcom/opensource/camera-devicetree"

DTC := "${KBUILD_OUTPUT}/scripts/dtc/dtc"
KERNEL_INCLUDE := "${STAGING_KERNEL_DIR}/include/"

CAMERA_ARCH = ""
COMPATIBLE_MACHINE = "qcm6490|qcs9100|qcs6490"
BOARD_NAME = "idp|core|vision"

python get_soc_family() {
    need_machine = d.getVar('COMPATIBLE_MACHINE')
    need_board = d.getVar('BOARD_NAME')
    import re
    if need_machine:
        compat_machines = (d.getVar('MACHINEOVERRIDES') or "").split(":")
        for m in compat_machines:
            if re.match(need_machine, m):
                d.appendVar('SOC_FAM', m)
                break
    if need_board:
        compat_boards = (d.getVar('MACHINE') or "").split("-")
        for m in compat_boards:
            if re.match(need_board, m):
                d.appendVar('BOARD', m)
                break

    soc_family =  d.getVar('SOC_FAM')
    if re.match(soc_family, 'qcm6490'):
        d.appendVar('CAMERA_ARCH', soc_family);
    if re.match(soc_family, 'qcs9100'):
        d.appendVar('CAMERA_ARCH', soc_family);
}

do_compile[prefuncs] += "get_soc_family"

EXTRA_OEMAKE += "CAMERA_ARCH='${CAMERA_ARCH}' MACHINE='${SOC_FAM}'"
EXTRA_OEMAKE += "DTC='${DTC}' KERNEL_INCLUDE='${KERNEL_INCLUDE}'"

do_compile() {
    if [ "${SOC_FAM}" = "qcm6490" ]; then
        if [ "${BOARD}" = "idp" ]; then
            oe_runmake ${EXTRA_OEMAKE} qcm6490-camera-idp
        elif [ "${BOARD}" = "core" ] || [ "${BOARD}" = "vision" ]; then
            oe_runmake ${EXTRA_OEMAKE} qcm6490-camera-rb3
            oe_runmake ${EXTRA_OEMAKE} qcm5430-camera-rb3
        else
            oe_runmake ${EXTRA_OEMAKE} qcm6490-camera-idp
            oe_runmake ${EXTRA_OEMAKE} qcm6490-camera-rb3
            oe_runmake ${EXTRA_OEMAKE} qcm5430-camera-rb3
        fi
    else
        echo "Unknown SOC_FAM -> " ${SOC_FAM}
    fi
}

do_install() {
    :
}

do_deploy() {
    echo "DTBO Staging path -> " ${DEPLOYDIR}/tech_dtbs
    install -d ${DEPLOYDIR}/tech_dtbs
    install -m 0644 ${S}/*.dtbo ${DEPLOYDIR}/tech_dtbs
}

addtask do_deploy after do_install
