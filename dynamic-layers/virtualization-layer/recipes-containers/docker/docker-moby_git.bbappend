do_install:append:qcom(){
    if ${@bb.utils.contains('DISTRO_FEATURES','systemd','true','false',d)}; then
        sed -i 's|ExecStart=/usr/bin/dockerd -H fd://|ExecStart=/usr/bin/dockerd  --exec-opt native.cgroupdriver=systemd  -H fd://|' ${D}/${systemd_unitdir}/system/docker.service
    fi
}
