inherit autotools

DESCRIPTION = "libgpt library"

LICENSE = "BSD-3-Clause-Clear"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/${LICENSE};md5=7a434440b651f4a472ca93716d01033a"

SRC_URI = "file://abctl/libgpt"

S = "${WORKDIR}/abctl/libgpt"
