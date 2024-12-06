inherit module deploy

DESCRIPTION = "QCOM Camera device-tree"

LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/${LICENSE};md5=550794465ba0ec5312d6919e203a55f9"

SRCPROJECT = "git://git.codelinaro.org/clo/le/platform/vendor/opensource/camera-devicetree.git;protocol=https"
SRCBRANCH  = "camera-kernel.qclinux.1.0.r1-rel"
SRCREV     = "1469be7d810e3ebfdacdd0a69f5b93c3fb38eb68"

SRC_URI = "${SRCPROJECT};branch=${SRCBRANCH};destsuffix=vendor/qcom/opensource/camera-devicetree"

DEPENDS:qcom-custom-bsp += "cameradlkm"

S = "${WORKDIR}/vendor/qcom/opensource/camera-devicetree"

DTC := "${KBUILD_OUTPUT}/scripts/dtc/dtc"
KERNEL_INCLUDE := "${STAGING_KERNEL_DIR}/include/"

COMPATIBLE_MACHINE = "qcm6490|qcs9100|qcs6490|qcs8300"
KODIAK_BOARD_NAMES = "qcm6490-idp|qcs6490-rb3gen2-vision-kit|qcs6490-rb3gen2-core-kit|"
LEMANS_BOARD_NAMES = "qcs9100-ride-sx|qcs9075-ride-sx|qcs9075-rb8-core-kit|"
MONACO_BOARD_NAMES = "qcs8300-ride-sx"

python get_soc_family() {
    need_machine = d.getVar('COMPATIBLE_MACHINE')
    all_boards = d.getVar('KODIAK_BOARD_NAMES')
    all_boards += d.getVar('LEMANS_BOARD_NAMES')
    all_boards += d.getVar('MONACO_BOARD_NAMES')

    import re
    compat_machines = (d.getVar('MACHINEOVERRIDES') or "").split(":")
    for m in compat_machines:
        if re.match(need_machine, m):
            d.appendVar('SOC_FAM', m)
            break
    target_board = (d.getVar('MACHINEOVERRIDES') or "").split(":")
    for m in target_board:
        if re.match(all_boards, m):
            d.appendVar('TARGET_BOARD', m)
            break

    soc_family =  d.getVar('SOC_FAM')
}

do_compile[prefuncs] += "get_soc_family"

EXTRA_OEMAKE += "DTC='${DTC}' KERNEL_INCLUDE='${KERNEL_INCLUDE}'"

do_compile() {
    if [ "${SOC_FAM}" = "qcm6490" ]; then
        if [ "${TARGET_BOARD}" = "qcm6490-idp" ]; then
            oe_runmake ${EXTRA_OEMAKE} qcm6490-camera-idp
        elif [ "${TARGET_BOARD}" = "qcs6490-rb3gen2-vision-kit" ] || \
             [ "${TARGET_BOARD}" = "qcs6490-rb3gen2-core-kit" ]; then
            oe_runmake ${EXTRA_OEMAKE} qcm6490-camera-rb3
            oe_runmake ${EXTRA_OEMAKE} qcm5430-camera-rb3
        else
            oe_runmake ${EXTRA_OEMAKE} qcm6490-camera-idp
            oe_runmake ${EXTRA_OEMAKE} qcm6490-camera-rb3
            oe_runmake ${EXTRA_OEMAKE} qcm5430-camera-rb3
        fi
    elif [ "${SOC_FAM}" = "qcs9100" ]; then
        if [ "${TARGET_BOARD}" = "qcs9100-ride-sx" ]; then
            oe_runmake ${EXTRA_OEMAKE} qcs9100-ride-sx-camera
        elif [ "${TARGET_BOARD}" = "qcs9075-ride-sx" ]; then
            oe_runmake ${EXTRA_OEMAKE} qcs9075-ride-sx-camera
        elif [ "${TARGET_BOARD}" = "qcs9075-rb8-core-kit" ]; then
            oe_runmake ${EXTRA_OEMAKE} qcs9075-camera-rb8
        fi
    elif [ "${SOC_FAM}" = "qcs8300" ]; then
        if [ "${TARGET_BOARD}" = "qcs8300-ride-sx" ]; then
            oe_runmake ${EXTRA_OEMAKE} qcs8300-camera
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
