do_install:append:qcom(){
    sed -i '/WantedBy=multi-user.target/d' ${D}/lib/systemd/system/kubelet.service
    sed -i '$a Environment="KUBELET_EXTRA_ARGS=--fail-swap-on=false"' ${D}/lib/systemd/system/kubelet.service.d/10-kubeadm.conf
}
