inherit autotools pkgconfig

LICENSE = "BSD-3-Clause-Clear"
LIC_FILES_CHKSUM = "file://${QCOM_COMMON_LICENSE_DIR}${LICENSE};md5=3771d4920bd6cdb8cbdf1e8344489ee0"

DESCRIPTION = "DAC and Mercury codecs test app"

SRCPROJECT = "git://git.codelinaro.org/clo/le/platform/vendor/qcom-opensource/audio-utils.git;protocol=https"
SRCBRANCH  = "audio-utils.lnx.1.0.r1-rel"
SRCREV     = "1154a270f52ff7c2e21cf9444b8799f98fe06b8f"

SRC_URI = "${SRCPROJECT};branch=${SRCBRANCH};destsuffix=audio/opensource/audio-utils"

S = "${WORKDIR}/audio/opensource/audio-utils/audio-plugins/test/dac_mer_test"

SOLIBS = ".so"
FILES_SOLIBSDEV = ""

