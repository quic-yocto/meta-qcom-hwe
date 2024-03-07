# Copyright (c) 2023 Qualcomm Innovation Center, Inc. All rights reserved.
# SPDX-License-Identifier: BSD-3-Clause-Clear

# This bbclass is designed to repack kernel image as a UKI (Unified Kernel Image)
#
# The UKI is composed by:
#   - an UEFI stub, from systemd-boot
#   - the kernel Image
#   - an initramfs
#   - other metadata like cmdline

inherit python3native

DEPENDS:append = " \
            systemd-boot-native \
            python3-native \
            python3-pefile-native \
            os-release \
            "

inherit features_check
REQUIRED_DISTRO_FEATURES += "systemd"

do_uki[depends] += " \
    systemd-boot:do_deploy \
    virtual/kernel:do_deploy \
    "
do_uki[depends] += "${@ '${INITRAMFS_IMAGE}:do_image_complete' if d.getVar('INITRAMFS_IMAGE') else ''}"

do_uki[cleandirs] = "${B}"
do_uki[dirs] = "${B}"

python do_uki() {
    import glob
    import subprocess
    import os

    # Construct the ukify command
    ukify_cmd = ("ukify build")

    deploy_dir_image = d.getVar('DEPLOY_DIR_IMAGE')

    # clean up uki.efi
    uki_file = deploy_dir_image + "/uki.efi"
    if os.path.exists(uki_file):
        os.remove(uki_file)

    # Ramdisk
    if d.getVar('INITRAMFS_IMAGE'):
        initrd_image_name = "%s-%s" % (d.getVar("INITRAMFS_IMAGE"), d.getVar("MACHINE"))
        baseinitrd = os.path.join(d.getVar("DEPLOY_DIR_IMAGE"), initrd_image_name)
        for img in (".cpio.gz", ".cpio.lz4", ".cpio.lzo", ".cpio.lzma", ".cpio.xz", ".cpio"):
            if os.path.exists(baseinitrd + img):
                initrd = baseinitrd + img
                break
        if not initrd:
            bb.fatal("ERROR: cannot find {initrd}.")
        ukify_cmd += " --initrd=%s" % initrd
    else:
        bb.fatal("ERROR - Required argument: INITRD")

    # Kernel
    if d.getVar('KERNEL_IMAGETYPE'):
        kernel = "%s/%s" % (deploy_dir_image, d.getVar('KERNEL_IMAGETYPE'))
        kernel_version = d.getVar('KERNEL_VERSION')
        if not os.path.exists(kernel):
            bb.fatal(f"ERROR: cannot find {kernel}.")
        ukify_cmd += " --linux=%s --uname %s" % (kernel, kernel_version)
    else:
        bb.fatal("ERROR - Required argument: KERNEL")

    # Kernel cmdline
    cmdline = "%s" %(d.getVar('KERNEL_CMDLINE_EXTRA'))
    ukify_cmd += " --cmdline='%s'" % (cmdline)

    # Architecture
    target_arch = d.getVar('EFI_ARCH')
    ukify_cmd += " --efi-arch %s" % target_arch

    # Stub
    stub = "%s/linux%s.efi.stub" % (deploy_dir_image, target_arch)
    if not os.path.exists(stub):
        bb.fatal(f"ERROR: cannot find {stub}.")
    ukify_cmd += " --stub %s" % stub

    # Add option for dtb
    if d.getVar('KERNEL_DEVICETREE') and d.getVar('UKI_DTB') == "1" :
        first_dtb = d.getVar('KERNEL_DEVICETREE').split()[0]
        dtbf = os.path.basename(first_dtb)
        dtb_path = "%s/%s/%s" % (deploy_dir_image, "DTOverlays", dtbf)

        if not os.path.exists(dtb_path):
            bb.fatal(f"ERROR: cannot find {dtb_path}.")
        ukify_cmd += " --devicetree %s" % dtb_path
    # Os-release
    osrelease = d.getVar('RECIPE_SYSROOT') + d.getVar('libdir') + "/os-release"
    ukify_cmd += " --os-release @%s" % osrelease

    # Custom UKI name
    output = " --output=%s" % d.getVar('UKI_FILENAME')
    ukify_cmd += " %s" % output

    # Set env to determine where bitbake should look for dynamic libraries
    env = os.environ.copy() # get the env variables
    env['LD_LIBRARY_PATH'] = d.expand("${RECIPE_SYSROOT_NATIVE}/usr/lib/systemd:${LD_LIBRARY_PATH}")

    # Run the ukify command
    subprocess.check_call(ukify_cmd, env=env, shell=True)
}

inherit deploy

do_deploy () {
    # Copy generated UKI into DEPLOYDIR
    install ${B}/${UKI_FILENAME} ${DEPLOYDIR}
}

addtask uki before do_deploy do_image after do_rootfs do_merge_dtbos
addtask deploy before do_build after do_compile
