# Avoid warnings when OSTree is used for SOTA

do_install:append:qcom(){
    # cronie binary handles creating /var/spool/cron/
    rm -rf ${D}${localstatedir}
}
