inherit autotools-brokensep pkgconfig

DESCRIPTION = "Bluetooth Vendor Library"
LICENSE = "Apache-2.0"

LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/${LICENSE};md5=89aea4e17d99a7cacdbeed46a0096b10"

DEPENDS = "common hci-qcomm-init glib-2.0"

SRC_URI += "git://git.codelinaro.org/clo/le/platform/hardware/qcom/bt.git;protocol=https;rev=216da8dd028c739e82869447e64675f3c712ecf7;branch=bt-performant.qclinux.1.0.r1-rel;destsuffix=bluetooth/libbt-vendor"

S = "${WORKDIR}/bluetooth/libbt-vendor/libbt-vendor"

EXTRA_OECONF = "--with-common-includes="${WORKSPACE}/bluetooth/bt_audio/hal/include/" \
                --with-lib-path=${STAGING_LIBDIR} \
                --with-glib \
               "

FILES:${PN} += "${userfsdatadir}/misc/bluetooth/*"

do_install:append () {
    install -d ${D}${userfsdatadir}/misc/bluetooth
    install -m 755 ${S}/init.msm.bt.sh ${D}${userfsdatadir}/misc/bluetooth/
}
