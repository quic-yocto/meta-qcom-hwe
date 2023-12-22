inherit autotools-brokensep pkgconfig

DESCRIPTION = "Build Google modp_b64"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/files/common-licenses/\
${LICENSE};md5=89aea4e17d99a7cacdbeed46a0096b10"

PR = "r0"

FILESPATH =+ "${WORKSPACE}/:"
SRC_URI = "git://git.codelinaro.org/clo/la/platform/external/modp_b64;protocol=ssh;nobranch=1;rev=2df9f7bc7b980f78e0b367123fcbe1ae94077859;destsuffix=modp_b64"
SRC_URI += "file://0001-Add-Support-to-build-libmodpb64.patch"

CXXFLAGS += "-I${S}/modp_64"

S = "${WORKDIR}/modp_b64"
CPPFLAGS:append = " ${@bb.utils.contains('VARIANT', 'debug', '-g', '', d)}"
#PARALLEL_MAKE = ""
