# Disable Automatic IP assignment for rmnet_ipa,qmapmux interfaces
do_install:append:qcom () {
    echo "" >> ${D}${sysconfdir}/dhcpcd.conf
    echo "# To avoid automatic IP assignment when connected to cellular network," >> ${D}${sysconfdir}/dhcpcd.conf
    echo "# denied auto assignment to qmapmux and rmnet_ipa interfaces." >> ${D}${sysconfdir}/dhcpcd.conf
    echo "denyinterfaces rmnet_ipa* qmapmux*" >> ${D}${sysconfdir}/dhcpcd.conf
}
