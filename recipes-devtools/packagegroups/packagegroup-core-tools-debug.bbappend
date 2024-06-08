# appends poky/meta/recipes-core/packagegroups/packagegroup-core-tools-debug.bb

RDEPENDS:${PN}:append = " \
    ltrace \
    perf \
    sysstat \
    valgrind \
"
