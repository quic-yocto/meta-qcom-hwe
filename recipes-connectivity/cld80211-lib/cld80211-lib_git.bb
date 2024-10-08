inherit autotools-brokensep pkgconfig

DESCRIPTION = "CLD80211 LIB"

LICENSE = "BSD-3-Clause & BSD-3-Clause-Clear"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/BSD-3-Clause;md5=550794465ba0ec5312d6919e203a55f9"
LIC_FILES_CHKSUM += "file://${COMMON_LICENSE_DIR}/BSD-3-Clause-Clear;md5=7a434440b651f4a472ca93716d01033a"

PV = "7.0"

DEPENDS += "libnl"

PACKAGE_ARCH = "${SOC_ARCH}"

SRCPROJECT = "git://git.codelinaro.org/clo/le/platform/hardware/qcom/wlan.git;protocol=https"
SRCBRANCH  = "wlan-os-service.qclinux.1.1.r1-rel"
SRCREV     = "a6d801edeb68336a2fb730c33e502c3a742327b3"

SRC_URI = "${SRCPROJECT};branch=${SRCBRANCH};destsuffix=hardware/qcom/wlan"

S = "${WORKDIR}/hardware/qcom/wlan/cld80211-lib"
CFLAGS += "-I ${STAGING_INCDIR}/libnl3"
