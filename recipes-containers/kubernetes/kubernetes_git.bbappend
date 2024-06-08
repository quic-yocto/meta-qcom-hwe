do_install:append:qcom(){
    sed -i '$a Environment="KUBELET_EXTRA_ARGS=--fail-swap-on=false"' ${D}/lib/systemd/system/kubelet.service.d/10-kubeadm.conf
}
