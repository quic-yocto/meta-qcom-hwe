inherit autotools pkgconfig systemd

PACKAGE_ARCH = "${MACHINE_ARCH}"

DESCRIPTION = "display Library"
LICENSE = "BSD-3-Clause & BSD-3-Clause-Clear"
BSD-3-Clause_LICENSE  = "file://sdm/include/core/display_interface.h;beginline=2;endline=22"
BSD-3-Clause-Clear_LICENSE = "file://sdm/include/core/display_interface.h;beginline=28;endline=29"

LIC_FILES_CHKSUM = " \
${BSD-3-Clause-Clear_LICENSE};md5=de893869f66f7d366d6b07f5cec50842 \
${BSD-3-Clause_LICENSE};md5=ef93dc3f1e145b6c1f89b90a5230ef8a"

SRC_URI = "git://git.codelinaro.org/clo/le/platform/hardware/qcom/display.git;protcol=https;rev=9374744efdb51d96f4971e59abaa0b40b84e7d38;branch=display.qclinux.1.0.r1-rel"

S = "${WORKDIR}/git"

EXTRA_OECONF += " --with-sanitized-headers=${STAGING_INCDIR}/linux-kernel-qcom/usr/include"
EXTRA_OECONF += " --enable-displayle"

PACKAGECONFIG ?= " \
                 ${@bb.utils.contains('COMBINED_FEATURES', 'drm', 'drm', '', d)} \
                 "

PACKAGECONFIG[drm] = "--enable-sdmhaldrm, --disable-sdmhaldrm, libdrm, libdrm"

DEPENDS += "libdrm \
            gbm \
            linux-kernel-qcom-headers \
            displaydlkm \
            "

SOLIBS = ".so"
FILES_SOLIBSDEV = ""
