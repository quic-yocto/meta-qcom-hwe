inherit useradd

DESCRIPTION = "Creates user accounts needed for QCOM BSP to work"

LICENSE = "BSD-3-Clause-Clear"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/${LICENSE};md5=7a434440b651f4a472ca93716d01033a"

do_configure() {
    :
}

do_compile() {
    :
}

do_install() {
    :
}

PACKAGES =+ "${PN}-system"

USERADD_PACKAGES = "${PN}-system"
GROUPADD_PARAM:${PN}-system = "--system system"
USERADD_PARAM:${PN}-system  = "--system --no-create-home --groups system --gid system system"
