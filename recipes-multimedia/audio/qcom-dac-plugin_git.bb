inherit autotools pkgconfig

LICENSE = "BSD-3-Clause-Clear"
LIC_FILES_CHKSUM = "file://${QCOM_COMMON_LICENSE_DIR}${LICENSE};md5=3771d4920bd6cdb8cbdf1e8344489ee0"

DESCRIPTION = "Audio DAC Plugin"
PR = "r0"

FILESPATH  =+ "${WORKSPACE}:"

SRC_URI = "git://git.codelinaro.org/clo/le/platform/vendor/qcom-opensource/audio-utils.git;protocol=https;rev=1154a270f52ff7c2e21cf9444b8799f98fe06b8f;branch=audio-utils.lnx.1.0.r1-rel;destsuffix=audio/opensource/audio-utils"

S = "${WORKDIR}/audio/opensource/audio-utils/audio-plugins/dac_plugin"

DEPENDS = "qcom-audio-plugin-headers"

SOLIBS = ".so"
FILES_SOLIBSDEV = ""
