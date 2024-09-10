# appends poky/meta/recipes-core/packagegroups/packagegroup-core-tools-debug.bb

RDEPENDS:${PN}:append:qcom = " \
    ltrace \
    perf \
    sysstat \
    valgrind \
"
