do_install:append:qcom () {
    sed -i -e 's:#PasswordAuthentication yes:PasswordAuthentication yes:' ${D}${sysconfdir}/ssh/sshd_config
}