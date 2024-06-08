# Copyright (c) 2024 Qualcomm Innovation Center, Inc. All rights reserved.
# SPDX-License-Identifier: BSD-3-Clause-Clear

python __anonymous () {
    if bb.data.inherits_class('module', d):
        bb.build.addtask("module_signing", "do_package_write_ipk", "do_package", d)
}

do_module_signing[depends] += "make-mod-scripts:do_compile"
do_module_signing[dirs] += "${STAGING_KERNEL_BUILDDIR}"
python do_module_signing() {
    import subprocess
    import os

    pkgdest = d.getVar('PKGDEST')
    for root, dirs, files in os.walk(pkgdest):
      for fl in files:
        file = os.path.join(root, fl)
        if file.endswith(".ko") and file.find("/lib/modules/") != -1:
          if oe.package.is_kernel_module_signed(os.path.join(root, file)):
            bb.debug(1, "Already signed module %s" % file)
          else:
            bb.debug(1, "Signing module %s" % file)
            kernelconf = ".config"
            signfile =  "scripts/sign-file"
            sighash = "sha512"  # get from config
            pvtkey =  "certs/signing_key.pem"
            pubkey =  "certs/signing_key.x509"
            cmd_sign = "%s %s %s %s %s" %(signfile, sighash, pvtkey, pubkey, file)

            # Set env to determine where bitbake should look for dynamic libraries
            env = os.environ.copy() # get the env variables
            env['LD_LIBRARY_PATH'] = d.expand("${RECIPE_SYSROOT_NATIVE}/usr/lib:${LD_LIBRARY_PATH}")
            subprocess.check_call(cmd_sign, env=env, shell=True)
}
