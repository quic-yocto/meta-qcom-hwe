SECTION = "kernel"

SUMMARY = "Linux Kernel headers sanitization tool"
DESCRIPTION = "Provides headers_install.sh script to sanitize kernel headers."

LICENSE = "GPLv2.0-with-linux-syscall-note"
LIC_FILES_CHKSUM = "file://COPYING;md5=6bc538ed5bd9a7fc9398086aedcd7e46"

SRC_URI += "git://git.codelinaro.org/clo/la/kernel/qcom.git;protocol=https;rev=7fbc1a30b3456be4b48589cfd2f9d15bc8f75d8a;branch=kernel.qclinux.1.0.r1-rel"
S = "${WORKDIR}/git"

DEPENDS += "unifdef-native"

do_configure[noexec] = "1"
do_compile[noexec] = "1"

do_install () {
    install -d ${D}/${bindir}
    cp ${S}/scripts/headers_install.sh ${D}/${bindir}
    sed -i 's/scripts\/unifdef/unifdef/g' ${D}/${bindir}/headers_install.sh
}

# Allow to build empty main package, to include -dev package into the SDK
ALLOW_EMPTY_${PN} = "1"

INHIBIT_DEFAULT_DEPS = "1"

BBCLASSEXTEND = "native"
