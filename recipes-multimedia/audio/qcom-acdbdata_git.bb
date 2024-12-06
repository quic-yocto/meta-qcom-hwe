inherit autotools pkgconfig

LICENSE = "BSD-3-Clause-Clear"
LIC_FILES_CHKSUM = "file://${QCOM_COMMON_LICENSE_DIR}${LICENSE};md5=3771d4920bd6cdb8cbdf1e8344489ee0"

DESCRIPTION = "Audio Calibration Library"

SRCPROJECT = "git://git.codelinaro.org/clo/le/platform/vendor/qcom-opensource/audioreach-conf.git;protocol=https"
SRCBRANCH  = "audio-core.lnx.1.0.r1-rel"
SRCREV     = "8ada127da2892cb6b59bcb823523f13c35a93cb9"

SRC_URI = "${SRCPROJECT};branch=${SRCBRANCH};destsuffix=audio/opensource/audioreach-conf"

S = "${WORKDIR}/audio/opensource/audioreach-conf/ar-acdb/acdbdata"

do_install:append:qcm6490() {
    mkdir -p -m 0777 ${D}${sysconfdir}/acdbdata
    mkdir -p -m 0755 ${D}${sysconfdir}/acdbdata/qcm6490_idp
    install -m 0644 ${S}/qcm6490/qcm6490_idp/acdb_cal.acdb ${D}${sysconfdir}/acdbdata/qcm6490_idp/acdb_cal.acdb
    install -m 0644 ${S}/qcm6490/qcm6490_idp/workspaceFileXml.qwsp ${D}${sysconfdir}/acdbdata/qcm6490_idp/workspaceFileXml.qwsp

    mkdir -p -m 0755 ${D}${sysconfdir}/acdbdata/qcm6490_rb3
    install -m 0644 ${S}/qcm6490/qcm6490_rb3/acdb_cal.acdb ${D}${sysconfdir}/acdbdata/qcm6490_rb3/acdb_cal.acdb
    install -m 0644 ${S}/qcm6490/qcm6490_rb3/workspaceFileXml.qwsp ${D}${sysconfdir}/acdbdata/qcm6490_rb3/workspaceFileXml.qwsp

    mkdir -p -m 0755 ${D}${sysconfdir}/acdbdata/qcm6490_rb3_vision
    install -m 0644 ${S}/qcm6490/qcm6490_rb3_vision/acdb_cal.acdb ${D}${sysconfdir}/acdbdata/qcm6490_rb3_vision/acdb_cal.acdb
    install -m 0644 ${S}/qcm6490/qcm6490_rb3_vision/workspaceFileXml.qwsp ${D}${sysconfdir}/acdbdata/qcm6490_rb3_vision/workspaceFileXml.qwsp

    mkdir -p -m 0755 ${D}${sysconfdir}/acdbdata/qcm6490_rb3_ia
    install -m 0644 ${S}/qcm6490/qcm6490_rb3_ia/acdb_cal.acdb ${D}${sysconfdir}/acdbdata/qcm6490_rb3_ia/acdb_cal.acdb
    install -m 0644 ${S}/qcm6490/qcm6490_rb3_ia/workspaceFileXml.qwsp ${D}${sysconfdir}/acdbdata/qcm6490_rb3_ia/workspaceFileXml.qwsp
}

do_install:append:qcs8300() {
    mkdir -p -m 0755 ${D}${sysconfdir}/acdbdata/qcs8300_ridesx
    install -m 0644 ${S}/qcs8300/qcs8300_ridesx/acdb_cal.acdb ${D}${sysconfdir}/acdbdata/qcs8300_ridesx/acdb_cal.acdb
    install -m 0644 ${S}/qcs8300/qcs8300_ridesx/workspaceFileXml.qwsp ${D}${sysconfdir}/acdbdata/qcs8300_ridesx/workspaceFileXml.qwsp
}

do_install:append:qcs9100() {
    mkdir -p -m 0755 ${D}${sysconfdir}/acdbdata/qcs9100_ridesx
    install -m 0644 ${S}/qcs9100/qcs9100_ridesx/acdb_cal.acdb ${D}${sysconfdir}/acdbdata/qcs9100_ridesx/acdb_cal.acdb
    install -m 0644 ${S}/qcs9100/qcs9100_ridesx/workspaceFileXml.qwsp ${D}${sysconfdir}/acdbdata/qcs9100_ridesx/workspaceFileXml.qwsp

    mkdir -p -m 0755 ${D}${sysconfdir}/acdbdata/qcs9075_rb8
    install -m 0644 ${S}/qcs9075/qcs9075_rb8/acdb_cal.acdb ${D}${sysconfdir}/acdbdata/qcs9075_rb8/acdb_cal.acdb
    install -m 0644 ${S}/qcs9075/qcs9075_rb8/workspaceFileXml.qwsp ${D}${sysconfdir}/acdbdata/qcs9075_rb8/workspaceFileXml.qwsp
}

DEPENDS = "qcom-kvh2xml"

SOLIBS = ".so"

FILES_SOLIBSDEV = ""
