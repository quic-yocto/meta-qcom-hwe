do_install:append:qcom(){
    sed -i 's|ExecStart=/usr/bin/dockerd -H fd://|ExecStart=/usr/bin/dockerd  --exec-opt native.cgroupdriver=systemd  -H fd://|' ${D}/lib/systemd/system/docker.service
}
