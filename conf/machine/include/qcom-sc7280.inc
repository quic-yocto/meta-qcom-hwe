SOC_FAMILY = "sc7280"
require conf/machine/include/qti-common.inc
DEFAULTTUNE = "armv8-2a-crypto"
require conf/machine/include/arm/arch-armv8-2a.inc

MACHINE_ESSENTIAL_EXTRA_RRECOMMENDS += " \
    pd-mapper \
    qrtr \
    rmtfs \
    tqftpserv \
"

MACHINE_EXTRA_RRECOMMENDS += " \
    fastrpc \
"
