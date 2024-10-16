SUMMARY = "Qualcomm linux kernel merged dtb creation"

DESCRIPTION = "Generates a single dtb by merging multiple dtbo files."

LICENSE = "BSD-3-Clause-Clear"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/BSD-3-Clause-Clear;md5=7a434440b651f4a472ca93716d01033a"

COMPATIBLE_HOST = '(arm.*|aarch64.*)-(linux.*)'

DEPENDS = "dtc-native"

do_fetch[noexec] = "1"
do_unpack[noexec] = "1"
do_patch[noexec] = "1"

SRC_URI = ""

KERNEL_TECH_DTBO_PROVIDERS ?= ""
# 'qcom-base-bsp' don't have dtb overlay support
KERNEL_TECH_DTBO_PROVIDERS:qcom-base-bsp = ""

def get_dtbo_providers(d) :
    dtbo_providers = ""
    for recipe in d.getVar('KERNEL_TECH_DTBO_PROVIDERS').split():
        dtbo_providers = recipe + ":do_deploy " + dtbo_providers

    return dtbo_providers

do_configure[depends] += " \
    virtual/kernel:do_deploy \
    ${@get_dtbo_providers(d)} \
    "

# Merge tech dtbos before generating dtb.bin
do_compile[cleandirs] = "${B}/DTOverlays"
python do_compile() {
    import os, shutil, subprocess

    fdtoverlay_bin = d.getVar('STAGING_BINDIR_NATIVE') + "/fdtoverlay"
    org_dtbo_dir = d.getVar('DEPLOY_DIR_IMAGE') + "/" + "tech_dtbs"
    dtbo_dir = d.getVar('B') + "/" + "merge_dtbs"
    dtoverlaydir = d.getVar('B') + "/" + "DTOverlays"
    os.makedirs(dtbo_dir, exist_ok=True)

    kernel_dt_var = "KERNEL_DEVICETREE:pn-" + d.getVar('PREFERRED_PROVIDER_virtual/kernel')
    kernel_dt = d.getVar('%s' % kernel_dt_var)

    for kdt in kernel_dt.split():
        org_kdtb = os.path.join(d.getVar('DEPLOY_DIR_IMAGE'), os.path.basename(kdt))

        # Rename and copy original kernel devicetree files
        kdtb = os.path.basename(org_kdtb) + ".0"
        shutil.copy2(org_kdtb, os.path.join(dtbo_dir, kdtb))

        # Find  and append matching dtbos for each dtb
        dtb = os.path.basename(org_kdtb)
        dtb_name = dtb.rsplit('.', 1)[0]
        dtbo_list =(d.getVarFlag('KERNEL_TECH_DTBOS', dtb_name) or "").split()
        bb.debug(1, "%s dtbo_list: %s" % (dtb_name, dtbo_list))
        dtbo_ignore_list = d.getVar('KERNEL_TECH_DTBOS_IGNORED') or ""
        bb.debug(1, "dtbo_ignore_list: %s" % dtbo_ignore_list)
        dtbos_found = 0
        for dtbo_file in dtbo_list:
            if dtbo_file in dtbo_ignore_list:
                bb.debug(1, "Ignored %s from merging into %s" %(dtbo_file, dtb))
                continue
            dtbos_found += 1
            dtbo = os.path.join(org_dtbo_dir, dtbo_file)
            pre_kdtb = os.path.join(dtbo_dir, dtb + "." + str(dtbos_found - 1))
            post_kdtb = os.path.join(dtbo_dir, dtb + "." + str(dtbos_found))
            cmd = fdtoverlay_bin + " -v -i "+ pre_kdtb +" "+ dtbo +" -o "+ post_kdtb
            bb.debug(1, "merge_dtbos cmd: %s" % (cmd))
            try:
                subprocess.check_output(cmd, shell=True)
            except RuntimeError as e:
                bb.error("cmd: %s failed with error %s" % (cmd, str(e)))
        if dtbos_found == 0:
            bb.debug(1, "No tech dtbos to merge into %s" % dtb)

        #Copy latest overlayed file into DTOverlays path
        output = dtb + "." + str(dtbos_found)
        shutil.copy2(os.path.join(dtbo_dir, output), os.path.join(dtoverlaydir, dtb))

        #Append final overlayed dtb file to combined-dtb.dtb
        combined_dtb = os.path.join(dtoverlaydir, "combined-dtb.dtb")
        with open(combined_dtb, 'ab') as fout:
            with open(os.path.join(dtoverlaydir, dtb), 'rb') as fin:
                bb.debug(1, "combining: %s" % os.path.join(dtoverlaydir,dtb))
                shutil.copyfileobj(fin, fout)
}

do_install() {
   install -m 0644 ${B}/DTOverlays/* ${D}
}

PACKAGE_ARCH = "${MACHINE_ARCH}"

PACKAGES = "${PN}-combined ${PN}"

FILES:${PN}-combined += "combined-dtb.dtb"
FILES:${PN} += "*.dtb"
