DESCRIPTION = "QCOM WLAN devicetree"
LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/${LICENSE};md5=550794465ba0ec5312d6919e203a55f9"

inherit module deploy

SRCPROJECT = "git://git.codelinaro.org/clo/le/platform/vendor/qcom-opensource/wlan/wlan-devicetree.git;protocol=https"
SRCBRANCH  = "wlan-platform.qclinux.1.0.r2-rel"
SRCREV     = "a0485a43966e5bb4e8b407260d179fa0285a8b68"

SRC_URI   =  "${SRCPROJECT};branch=${SRCBRANCH};destsuffix=wlan/wlan-devicetree \
	      ${@bb.utils.contains('PREFERRED_PROVIDER_virtual/kernel', 'linux-kernel-qcom-rt', 'file://0001-wlan-devicetree-disable-icnss-driver.patch', '', d)}"

S = "${WORKDIR}/wlan/wlan-devicetree"

DTC := "${KBUILD_OUTPUT}/scripts/dtc/dtc"
KERNEL_INCLUDE := "${STAGING_KERNEL_DIR}/include/"
EXTRA_OEMAKE += "DTC='${DTC}' KERNEL_INCLUDE='${KERNEL_INCLUDE}'"

COMPATIBLE_MACHINE = "qcm6490|qcs9100"

# The DTSO file names must start with '${COMPATIBLE_MACHINE}-'
DTBO_TARGETS = ""

python get_dtbo_targets() {
    need_machine = d.getVar('COMPATIBLE_MACHINE')
    if need_machine:
        import re
        compat_machines = (d.getVar('MACHINEOVERRIDES') or "").split(":")
        for m in compat_machines:
            if re.match(need_machine, m):
                dtso_dir = d.getVar('S')

                for f in os.listdir(dtso_dir):
                    if (re.match(m + '-.*.dtso', f)):
                        dtbo_target = os.path.splitext(os.path.basename(f))[0]
                        d.appendVar('DTBO_TARGETS', dtbo_target + " ")
                break

        if 'qcm6490' in compat_machines:
            d.appendVar('DTBO_TARGETS', 'qcs5430-wlan-upstream' + " ")
            d.appendVar('DTBO_TARGETS', 'qcs5430-wlan-rb3')
}

do_compile[prefuncs] += "get_dtbo_targets"

do_compile() {
    for dtbo_target in ${DTBO_TARGETS}; do
        oe_runmake ${EXTRA_OEMAKE} ${dtbo_target}
    done
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
