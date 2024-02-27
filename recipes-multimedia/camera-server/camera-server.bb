inherit cmake pkgconfig

DESCRIPTION = "Qualcomm Linux Embedded Camera Server"
LICENSE = "BSD-3-Clause-Clear"
LIC_FILES_CHKSUM = "file://${QCOM_COMMON_LICENSE_DIR}${LICENSE};md5=3771d4920bd6cdb8cbdf1e8344489ee0"

SSTATE_ALLOW_OVERLAP_FILES = "/"

DEPENDS += "glib-2.0"
DEPENDS += "gtest"
DEPENDS += "gbm"
DEPENDS:append:qcm6490 = " property-vault syslog-plumber protobuf-native protobuf-c protobuf-c-native camx "

RDEPENDS:${PN} += "gbm"

GBM_FREE_FD := "FALSE"

EXTRA_OECMAKE += "-DSYSROOT_INCDIR=${STAGING_INCDIR}"
EXTRA_OECMAKE += "-DSYSROOT_LIBDIR=${STAGING_LIBDIR}"
EXTRA_OECMAKE += "-DKERNEL_INCDIR=${STAGING_INCDIR}/linux-msm"
EXTRA_OECMAKE += "-DBUILD_CATEGORY=ALL"
EXTRA_OECMAKE += "-DTARGET_BOARD_PLATFORM=${BASEMACHINE}"
EXTRA_OECMAKE += "-DTARGET_PRODUCT_PLATFORM=${PRODUCT}"
EXTRA_OECMAKE += "-DCAM_SERVER_SYSTEMD_DIR=${sysconfdir}/systemd/system"
EXTRA_OECMAKE += "-DGBM_FREE_FD=${GBM_FREE_FD}"
EXTRA_OECMAKE:append:qcm6490 = "-DCMAKE_SYSROOT_NATIVE=${WORKDIR}/recipe-sysroot-native/"
# Currently BASEMACHINE is empty for 6490, so setting it manually
EXTRA_OECMAKE:remove:qcm6490 = "-DTARGET_BOARD_PLATFORM=${BASEMACHINE}"
EXTRA_OECMAKE:append:qcm6490 = " -DTARGET_BOARD_PLATFORM=qcm6490 "

SRC_URI += "git://git.codelinaro.org/clo/le/platform/vendor/qcom-opensource/le-services.git;protocol=https;rev=1d01b80c2fe043a4ff1d6b9bd06d93ab9e5d9ef0;branch=le-services.lnx.1.0.r1-rel \
           file://cam-server-env \
           "

S = "${WORKDIR}/git"

SOLIBS = ".so*"
FILES_SOLIBSDEV = ""

do_install:append () {
    if ${@bb.utils.contains('DISTRO_FEATURES', 'systemd', 'true', 'false', d)}; then
        install -d ${D}/etc/systemd/system/
        install -d ${D}/etc/systemd/system/multi-user.target.wants/
        # enable the service for multi-user.target
        ln -sf /etc/systemd/system/cam-server.service \
           ${D}/etc/systemd/system/multi-user.target.wants/cam-server.service
    fi
    install ${WORKDIR}/cam-server-env -D ${D}/${sysconfdir}/cam-server-env
}

FILES:${PN}-cam-server-dbg = "${bindir}/.debug/cam-server"
FILES:${PN}-cam-server     = "${bindir}/cam-server"
FILES:${PN}-cam-server    += "/etc/systemd/system/"

FILES:${PN}-libqmmf_recorder_client-dbg    = "${libdir}/.debug/libqmmf_recorder_client.*"
FILES:${PN}-libqmmf_recorder_client        = "${libdir}/libqmmf_recorder_client.so.*"
FILES:${PN}-libqmmf_recorder_client-dev    = "${libdir}/libqmmf_recorder_client.so ${libdir}/libqmmf_recorder_client.la ${includedir}"

FILES:${PN}-libqmmf_recorder_service-dbg    = "${libdir}/.debug/libqmmf_recorder_service.*"
FILES:${PN}-libqmmf_recorder_service        = "${libdir}/libqmmf_recorder_service.so.*"
FILES:${PN}-libqmmf_recorder_service-dev    = "${libdir}/libqmmf_recorder_service.so ${libdir}/libqmmf_recorder_service.la ${includedir}"

FILES:${PN}-libcamera_adaptor-dbg    = "${libdir}/.debug/libcamera_adaptor.*"
FILES:${PN}-libcamera_adaptor        = "${libdir}/libcamera_adaptor.so.*"
FILES:${PN}-libcamera_adaptor-dev    = "${libdir}/libcamera_adaptor.so ${libdir}/libcamera_adaptor.la ${includedir}"

INSANE_SKIP:${PN} += "build-deps dev-deps file-rdeps dev-so"
do_configure[depends] += "virtual/kernel:do_shared_workdir"

PACKAGE_ARCH = "${MACHINE_ARCH}"
