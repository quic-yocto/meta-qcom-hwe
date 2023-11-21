# appends poky/meta/recipes-core/packagegroups/packagegroup-core-tools-debug.bb

RDEPENDS:${PN}:append = " \
    valgrind \
    ltrace \
"

# Add debug support packages to RDEPENDS list for a debug build.
# Avoid gdb on target. On target gdb takes up considerable storage.
RDEPENDS:${PN}:remove = " \
    gdb \
    gdbserver \
"
