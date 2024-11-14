#
# Copyright (c) 2023 Qualcomm Innovation Center, Inc. All rights reserved.
# SPDX-License-Identifier: BSD-3-Clause-Clear

# For recipe inheriting 'qprebuilt', this class allows to use
# prebuilt package instead of fetching and compiling the source.
#

require conf/machine/include/qcom-prebuilts.inc

python qprebuilt_do_unpack() {
    src_uri = (d.getVar('SRC_URI') or "").split()
    if len(src_uri) == 0:
        return

    try:
        fetcher = bb.fetch2.Fetch(src_uri, d)
        fetcher.unpack(d.getVar('S'))
    except bb.fetch2.BBFetchException as e:
        bb.fatal("Bitbake Fetcher Error: " + repr(e))
}
do_unpack[dirs] = "${WORKDIR}"
do_unpack[cleandirs] = "${S}"

do_configure[noexec] = "1"
do_compile[noexec] = "1"

python qprebuilt_do_install() {
    import shutil

    dest = d.getVar('D')
    src  = d.getVar('S')
    # Copy all files into D
    cmd = "cp -r %s/* %s" %(src, dest)
    (retval, output) = oe.utils.getstatusoutput(cmd)
    if retval:
       bb.fatal("Errors in extracting prebuilt (%s)" % output)

    # Install license
    licensedir = d.getVar('LICENSE_DIRECTORY')
    pn = d.getVar("PN")
    cmd = "cp -r %s/__LIC__ %s/%s" % (dest, licensedir, pn)
    (retval, output) = oe.utils.getstatusoutput(cmd)
    if retval:
        bb.warn("Unable to retrieve license: %s" % output)

    shutil.rmtree("%s/__LIC__" % dest)
}

EXPORT_FUNCTIONS do_unpack do_install

INSANE_SKIP:${PN}:append = " already-stripped"
