DESCRIPTION = "QCOM WLAN devicetree"
LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/${LICENSE};md5=550794465ba0ec5312d6919e203a55f9"

inherit module deploy

SRC_URI += "git://git.codelinaro.org/clo/le/platform/vendor/qcom-opensource/wlan/wlan-devicetree.git;protocol=https;rev=691eec8b0a360751eb3104765bad0df0c2f8af9a;branch=wlan-platform.qclinux.1.0.r2-rel"

S = "${WORKDIR}/git"

DTC := "${KBUILD_OUTPUT}/scripts/dtc/dtc"
KERNEL_INCLUDE := "${STAGING_KERNEL_DIR}/include/"
EXTRA_OEMAKE += "DTC='${DTC}' KERNEL_INCLUDE='${KERNEL_INCLUDE}'"
DTSO_TARGETS := ""

# The DTSO file names must start with '${MACHINE}-'
python get_dtbo_targets() {
    import re
    dtso_dir = d.getVar('S')
    machine = d.getVar('MACHINE')

    for f in os.listdir(dtso_dir):
        if (re.match(machine + '-.*.dtso', f)):
            dtbo_target = os.path.splitext(os.path.basename(f))[0]
            d.appendVar('DTBO_TARGETS', dtbo_target + " ")
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
