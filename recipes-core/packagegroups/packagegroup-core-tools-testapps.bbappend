# appends poky/meta/recipes-core/packagegroups/packagegroup-core-tools-testapps.bb

RDEPENDS:${PN}:remove:qcom = "connman-tools"
RDEPENDS:${PN}:remove:qcom = "connman-tests"
RDEPENDS:${PN}:remove:qcom = "connman-client"
