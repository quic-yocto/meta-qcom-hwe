# expose system user to avoid "useradd: group 'system' does not exist"
DEPENDS:append:qcom = " useradd-qcom"

do_install:prepend:qcom() {
    # convert the service from root user to system user
    sed -i "/ExecStart=/i\User=system\nGroup=system" pd-mapper.service.in
}
