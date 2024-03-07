inherit autotools pkgconfig

DESCRIPTION = "Common mm-audio headers installation"

LICENSE = "BSD-3-Clause & BSD-3-Clause-Clear"
LIC_FILES_CHKSUM = " \
    file://${COMMON_LICENSE_DIR}/BSD-3-Clause;md5=550794465ba0ec5312d6919e203a55f9 \
    file://${COMMON_LICENSE_DIR}/BSD-3-Clause-Clear;md5=7a434440b651f4a472ca93716d01033a \
"

SRC_URI += "git://git.codelinaro.org/clo/le/platform/vendor/qcom/opensource/arpal-lx.git;protocol=https;rev=a01601b65d00946e8c2d9ce66eedefa5d4f9bd96;branch=audio-platform-arintf.lnx.2.0.r3-rel"

S = "${WORKDIR}/git/inc"

do_compile[noexec] = "1"

ALLOW_EMPTY:${PN} = "1"
