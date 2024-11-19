DESCRIPTION = "Data Plane Development Kit (DPDK)"
HOMEPAGE = "https://www.dpdk.org"
LICENSE = "BSD-3-Clause&LGPLv2.1&GPLv2.0"
LIC_FILES_CHKSUM = "file://license/gpl-2.0.txt;md5=b234ee4d69f5fce4486a80fdaf4a4263 \
                    file://license/lgpl-2.1.txt;md5=4b54a1fd55a448865a0b32d41598759d \
                    file://license/bsd-3-clause.txt;md5=0f00d99239d922ffd13cabef83b33444"

SRC_URI = "https://fast.dpdk.org/rel/dpdk-20.11.10.tar.xz"
SRC_URI[md5sum] = "ded4f65d0b2c7da97b922a718b629670"

S = "${WORKDIR}/dpdk-stable-20.11.10"

EXTRA_OEMESON = "-Dexamples=all \
"
RDEPENDS:${PN} += "pciutils python3-core bash"
RDEPENDS:${PN}-examples += "bash"

DEPENDS = "numactl python3-pyelftools-native"
inherit meson pkgconfig

INSTALL_PATH = "${prefix}/dpdk"

do_install:append(){
    # remove  source files
    rm -rf ${D}/${INSTALL_PATH}/examples/*

    # Install examples
    install -m 0755 -d ${D}/${INSTALL_PATH}/examples/
    for dirname in ${B}/examples/dpdk-*
    do
        if [ ! -d ${dirname} ] && [ -x ${dirname} ]; then
            install -m 0755 ${dirname} ${D}/${INSTALL_PATH}/examples/
        fi
    done

}

PACKAGES =+ "${PN}-examples ${PN}-tools"

FILES:${PN} += " ${bindir}/dpdk-testpmd \
                 ${bindir}/dpdk-proc-info \
                 ${libdir}/*.so* \
                 ${libdir}/dpdk/pmds-21.0/*.so* \
                 "
FILES:${PN}-examples = " \
        ${prefix}/dpdk/examples/* \
        "

FILES:${PN}-tools = " \
    ${bindir}/dpdk-pdump \
    ${bindir}/dpdk-test \
    ${bindir}/dpdk-test-* \
    ${bindir}/dpdk-*.py \
    "

CVE_PRODUCT = "data_plane_development_kit"

INSANE_SKIP:${PN} = "dev-so"


