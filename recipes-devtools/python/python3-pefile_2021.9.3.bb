inherit pypi
inherit setuptools3

SUMMARY = "Python PE parsing module"
HOMEPAGE = "https://github.com/erocarrera/pefile"

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=ef2c924be9d0c8d6cf82374769748bfa"

DEPENDS += "\
            python3-future-native \
           "

PYPI_PACKAGE = "pefile"

SRC_URI[sha256sum] = "344a49e40a94e10849f0fe34dddc80f773a12b40675bf2f7be4b8be578bdd94a"

BBCLASSEXTEND = "native"
