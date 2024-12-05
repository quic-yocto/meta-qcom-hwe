#FILESEXTRAPATHS:prepend:qcom := "${THISDIR}/${BPN}:"

FILESEXTRAPATHS:append := "${THISDIR}:"

SRC_URI += "file://selinux-qcom-autorelabel.sh  \
"

do_install:append(){

        install -d ${D}${bindir}
        install -m755 ${WORKDIR}/selinux-qcom-autorelabel.sh ${D}${bindir} 
        sed -i 's|ExecStart=/usr/bin/selinux-autorelabel.sh|ExecStart=/usr/bin/selinux-qcom-autorelabel.sh|g' ${D}${systemd_unitdir}/system/selinux-autorelabel.service

}
