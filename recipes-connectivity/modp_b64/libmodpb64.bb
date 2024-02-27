inherit autotools-brokensep pkgconfig

DESCRIPTION = "Build Google modp_b64"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/${LICENSE};md5=89aea4e17d99a7cacdbeed46a0096b10"

SRC_URI = "git://git.codelinaro.org/clo/la/platform/external/modp_b64;protocol=ssh;nobranch=1;rev=2df9f7bc7b980f78e0b367123fcbe1ae94077859;destsuffix=modp_b64"
SRC_URI += "file://0001-Add-Support-to-build-libmodpb64.patch"

CXXFLAGS += "-I${S}/modp_64"

S = "${WORKDIR}/modp_b64"
