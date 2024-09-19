# Remove /var/lib directory at build-time as creation of
# /var/lib directory is already handed by base-files

DEPENDS:append:class-target:qcom = " base-files "

do_install:append:qcom(){
    rm -rf ${D}/${localstatedir}/lib/
}
