inherit autotools pkgconfig cmake

DESCRIPTION = "Tinyalsa Library"
LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://NOTICE;md5=d2918795d9185efcbf430b9ad5cda46d"

SRCREV = "f78ed25aced2dfea743867b8205a787bfb091340"
SRC_URI = "git://git.codelinaro.org/clo/le/platform/external/tinyalsa.git;protocol=git;branch=github/master \
           file://0001-utils-tinywavinfo-Fix-datatype-of-variable.patch \
           file://0001-pcm-Fix-for-mmap-usecases.patch \
           file://0002-pcm-Add-plugin-support-for-pcm_ioctl.patch"

S = "${WORKDIR}/git"

PV = "1.1.1.qcom+git${SRCPV}"

DEPENDS = "glib-2.0"
EXTRA_OECONF += "--with-glib"
EXTRA_OECMAKE += "-DTINYALSA_USES_PLUGINS=1"
CFLAGS += " -fPIC -DPIC"
