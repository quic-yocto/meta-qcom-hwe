inherit autotools pkgconfig

LICENSE = "BSD-3-Clause-Clear"
LIC_FILES_CHKSUM = "file://${QCOM_COMMON_LICENSE_DIR}${LICENSE};md5=3771d4920bd6cdb8cbdf1e8344489ee0"

DESCRIPTION = "Audio Calibration Library"

SRC_URI = "git://git.codelinaro.org/clo/le/platform/vendor/qcom-opensource/audioreach-conf.git;protocol=https;rev=8ea5b214094d709c65da006b0fbb6f7c625fc9cd;branch=audio-core.lnx.1.0.r1-rel;destsuffix=audio/opensource/audioreach-conf"

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
}

DEPENDS = "kvh2xml"

SOLIBS = ".so"

FILES_SOLIBSDEV = ""
