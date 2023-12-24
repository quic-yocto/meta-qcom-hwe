#
# Copyright (c) 2023 Qualcomm Innovation Center, Inc. All rights reserved.
# SPDX-License-Identifier: BSD-3-Clause-Clear

# For recipe inheriting 'qprebuilt', this class allows to use
# prebuilt package instead of fetching and compiling the source.
#

BB_GIT_SHALLOW = "1"

# Fetch only the top commit
BB_GIT_SHALLOW_DEPTH = "1"

deltask do_patch
deltask do_configure
deltask do_compile
deltask do_populate_lic

addtask prepare_recipe_sysroot after do_unpack
addtask install after do_prepare_recipe_sysroot

fakeroot python do_install() {
    import shutil

    dest = d.getVar('D')
    tarball = d.getVar('S') +"/" + d.getVar('PREBUILT_TARBALL')
    bb.note("Install %s" % tarball)
    cmd = "tar -xvzf %s -C %s" % (tarball, dest)
    (retval, output) = oe.utils.getstatusoutput(cmd)
    if retval:
       bb.fatal("Errors in extracting prebuilt: %s (%s)" % ( tarball, output))

    # Install license
    licensedir = d.getVar('LICENSE_DIRECTORY')
    pn = d.getVar("PN")
    cmd = "cp -r %s/__LIC__ %s/%s" % (dest, licensedir, pn)
    (retval, output) = oe.utils.getstatusoutput(cmd)
    if retval:
        bb.warn("Unable to retrieve license: %s" % output)

    shutil.rmtree("%s/__LIC__" % dest)
}

INSANE_SKIP:${PN}:append = " already-stripped"
