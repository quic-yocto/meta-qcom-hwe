do_install:prepend() {
    # convert the service from root user to system user
    sed -i "/ExecStart=/i\User=system\nGroup=system" pd-mapper.service.in
}
