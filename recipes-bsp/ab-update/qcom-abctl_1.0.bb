inherit systemd autotools pkgconfig

DESCRIPTION = "abctl library and utility."

LICENSE = "BSD-3-Clause-Clear"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/${LICENSE};md5=7a434440b651f4a472ca93716d01033a"

DEPENDS += "qcom-libgpt libcap virtual/kernel"

SRC_URI = "git://git-android.quicinc.com/abctl.git;protocol=git;rev=010035b613f3170992a338cd99a7772b376a051d;branch=abctl.qclinux.1.0.r1-rel;destsuffix=abctl"

S = "${WORKDIR}/abctl/abctl"

PACKAGECONFIG ??= " \
    glib \
"
PACKAGECONFIG[glib] = "--with-glib, --without-glib, glib-2.0"

do_install:append() {
    install -d ${D}${systemd_unitdir}/system/
    install -m 0644 ${S}/ab-updater.service -D \
               ${D}${systemd_unitdir}/system/ab-updater.service
}

SYSTEMD_SERVICE:${PN} = "ab-updater.service"

FILES:${PN} += "${systemd_unitdir}/system/"
