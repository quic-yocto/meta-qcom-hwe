SUMMARY = "QCOM EVA Package Group"
inherit packagegroup

PACKAGES = "${PN}"

RDEPENDS:${PN} += " \
    eva-kernel \
    eva-devicetree \
    "

# EVA not present in the below mentioned MACHINEs
RDEPENDS:${PN}:qcm6490 = ""