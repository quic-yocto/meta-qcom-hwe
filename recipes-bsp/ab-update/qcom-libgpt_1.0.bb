inherit autotools

DESCRIPTION = "libgpt library"

LICENSE = "BSD-3-Clause-Clear"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/${LICENSE};md5=7a434440b651f4a472ca93716d01033a"

SRC_URI = "git://git-android.quicinc.com/abctl.git;protocol=git;rev=010035b613f3170992a338cd99a7772b376a051d;branch=abctl.qclinux.1.0.r1-rel;destsuffix=abctl"

S = "${WORKDIR}/abctl/libgpt"
