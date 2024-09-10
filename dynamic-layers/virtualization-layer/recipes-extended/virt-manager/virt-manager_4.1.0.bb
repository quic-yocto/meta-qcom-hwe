DESCRIPTION = "virt-manager is a graphical tool for managing virtual machines via libvirt"
HOMEPAGE = "https://virt-manager.org/"
LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://COPYING;md5=b234ee4d69f5fce4486a80fdaf4a4263"
DEPENDS += "python3-docutils-native"
SRCREV = "6710ca6969b7d9c4e8344acd0fe3d50b24adc8ec"

SRC_URI = " \
  git://github.com/virt-manager/virt-manager;branch=main;protocol=https \
  file://0001-setup.py-move-global-args-to-install-args.patch \
"

S = "${WORKDIR}/git"

PACKAGECONFIG ??= "gui"
PACKAGECONFIG[gui] = ",--no-update-icon-cache --no-compile-schemas,python3-pygobject"

inherit ${@bb.utils.contains('PACKAGECONFIG', 'gui', 'gtk-icon-cache', '', d)}
inherit bash-completion gettext pkgconfig setuptools3_legacy

PACKAGES += " \
  ${PN}-common \
  ${PN}-install \
"

RDEPENDS:${PN}-common += " \
  libvirt-python \
  libosinfo \
"

RDEPENDS:${PN} = "${PN}-common"
RDEPENDS:${PN}-install = "${PN}-common"

SETUPTOOLS_INSTALL_ARGS += "${PACKAGECONFIG_CONFARGS}"

FILES:${PN} = " \
  ${bindir}/virt-manager \
  ${datadir}/icons/* \
"

FILES:${PN}-common = " \
  ${libdir}/* \
  ${libdir}/python3.10/* \
  ${datadir}/applications \
  ${datadir}/virt-manager \
  ${datadir}/glib-2.0/* \
  ${datadir}/metainfo/* \
"

FILES:${PN}-install = " \
  ${bindir}/virt-clone \
  ${bindir}/virt-install \
  ${bindir}/virt-xml \
"
