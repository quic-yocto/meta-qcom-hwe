LIC_FILES_CHKSUM = "file://COPYING;md5=d79ee9e66bb0f95d3386a7acae780b70"

FILESEXTRAPATHS:prepend := "${THISDIR}/weston-launch:"

SRC_URI = "   file://weston.ini \
              file://weston.png \
              file://weston.desktop \
              file://xwayland.weston-start \
              file://systemd-notify.weston-start \
               git://git.codelinaro.org/clo/le/wayland/weston.git;protocol=https;rev=4aea656b8f0e059cadb6313d6b82e5563542e354;branch=display.qclinux.1.0.r1-rel;destsuffix=display/vendor/qcom/opensource/display/weston"

S = "${WORKDIR}/display/vendor/qcom/opensource/display/weston"

DEPENDS:append:qcom = " property-vault gbm display-hal-linux libdmabufheap"

EXTRA_OEMESON += "-Ddeprecated-wl-shell=true"
EXTRA_OEMESON += "-Dbackend-default=auto -Dbackend-rdp=false"

RRECOMMENDS:${PN} = "weston-launch liberation-fonts"

REQUIRED_DISTRO_FEATURES:remove:qcom = "opengl"

# select compositor, enable simple and demo clients and enable EGL
PACKAGECONFIG:qcom = " \
                 sdm \
                 egl \
                 clients \
                 shell-desktop \
                 disablepowerkey \
                 screenshare \
                 shell-fullscreen \
                 shell-ivi \
                 image-jpeg \
                 "

# Weston on SDM
PACKAGECONFIG[sdm] = "-Dbackend-sdm=true,-Dbackend-sdm=false"
# Weston with disabling display power key
PACKAGECONFIG[disablepowerkey] = "-Ddisable-power-key=true,-Ddisable-power-key=false"

LDFLAGS  += "-ldrmutils -ldisplaydebug -lglib-2.0 -ldmabufheap"

#meson script's CPP flags
CXXFLAGS += "-I${STAGING_INCDIR}/sdm"

do_install:append() {
    install -m 0644 ${WORKDIR}/weston.ini -D ${D}${sysconfdir}/xdg/weston/weston.ini
}

FILES:${PN} += "${bindir}/*"
FILES:${PN} += " ${libdir}/*.so"
FILES:${PN} += "${sysconfdir}/xdg/weston/weston.ini"
