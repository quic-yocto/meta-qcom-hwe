# Toolchain to use
TOOLCHAIN = "gcc"

# Common Dependencies
#DEPENDS += "system-core-headers"
DEPENDS += "libxml-simple-perl-native"
#DEPENDS += "libutils"
#DEPENDS += "liblog"
DEPENDS += "syslog-plumber"
#DEPENDS += "libcutils"
DEPENDS += "property-vault"
DEPENDS += "glib-2.0"
#DEPENDS += "libsync"

# Other Dependencies
DEPENDS += "gbm"
#DEPENDS += "video-kernel"
#DEPENDS += "adsprpc"
#DEPENDS += "linux-msm-headers"

# Extra Flags
TARGET_LDFLAGS:append   = " -Wl,--no-undefined"
CAMX_FLAGS  = "-target ${TARGET_SYS} "
CAMX_FLAGS := "-I ${STAGING_INCDIR}/c++ "
CAMX_FLAGS := "-I ${STAGING_INCDIR}/linux-msm/usr/include"
CAMX_FLAGS := "-I ${STAGING_KERNEL_BUILDDIR}/usr/include/vidc"

def config_target_sys(d):
    if d.getVar('PRODUCT', True) == 'ubuntu':
        return "aarch64-linux-gnu"
    else:
        return "${TARGET_ARCH}${TARGET_VENDOR}${@['-' + d.getVar('TARGET_OS'), ''][d.getVar('TARGET_OS') == ('' or 'custom')]}"

TARGET_SYS = "${@config_target_sys(d)}"

# Get the Machine name
def get_platform(d):
    if d.getVar('BASEMACHINE', True) == 'qrb5165':
        return "kona"
    else:
        return ""

#Append Extra Flags
OECMAKE_C_FLAGS:append   = " ${CAMX_FLAGS}"
OECMAKE_CXX_FLAGS:append = " ${CAMX_FLAGS}"

EXTRA_OECMAKE = "\
        -DCAMXDEBUG:STRING=True \
        -DPLATFORM:STRING=linux \
        -DCPU:STRING=64 \
        -DCMAKE_CROSSCOMPILING:BOOL=True \
        -DCMAKE_LIBRARY_PATH:PATH=${STAGING_LIBDIR} \
        -DCMAKE_INCLUDE_PATH:PATH=${STAGING_INCDIR} \
        -DKERNEL_INCDIR=${STAGING_INCDIR}/linux-msm \
        -DCAMX_PATH:PATH=${WORKDIR}/camx-kt \
        -DTARGET_BOARD_PLATFORM:STRING=${@get_platform(d)} \
"

# FR-69986: Recipe-Packages: Recipe must set PACKAGE_ARCH = ${MACHINE_ARCH} when codebase compiled is specific to targets
# Since complete camx is target dependent hence setting this here
PACKAGE_ARCH = "${MACHINE_ARCH}"
