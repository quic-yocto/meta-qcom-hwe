HOMEPAGE = "https://github.com/quic/fastrpc"

LIC_FILES_CHKSUM = "file://src/fastrpc_apps_user.c;beginline=1;endline=2;md5=a5b0aa365758f6917baf7a2d81f5d29e"

COMPATIBLE_MACHINE = "(qcom)"

SRCREV = "${AUTOREV}"

FILESEXTRAPATHS:prepend:qcom := "${THISDIR}/${BPN}:"

SRC_URI:remove:qcom = "\
    git://git.codelinaro.org/linaro/qcomlt/fastrpc.git;branch=automake;protocol=https \
    file://0001-apps_std_fopen_with_env-account-for-domain-kinds-whe.patch \
"

SRC_URI:append:qcom = "\
    git://github.com/quic/fastrpc.git;branch=main;protocol=https \
"

SYSTEMD_SERVICE:${PN}:remove = "usr-lib-rfsa.service"

SYSTEMD_SERVICE:${PN}-systemd = "adsprpcd.service cdsprpcd.service"

SYSTEMD_AUTO_ENABLE:${PN}-systemd = "enable"

do_install:append:qcom() {
    rm -rf ${D}${systemd_unitdir}/system/usr-lib-rfsa.service
    rm -rf ${D}${systemd_unitdir}/system/sdsprpcd.service

    rm -rf ${D}${sbindir}/mount-dsp.sh
    rm -rf ${D}${sbindir}
}
