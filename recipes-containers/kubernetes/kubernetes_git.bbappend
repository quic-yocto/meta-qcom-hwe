do_install:append:qcom(){
    sed -i '/WantedBy=multi-user.target/d' ${D}${nonarch_base_libdir}/systemd/system/kubelet.service
    sed -i '$a Environment="KUBELET_EXTRA_ARGS=--fail-swap-on=false"' ${D}${nonarch_base_libdir}/systemd/system/kubelet.service.d/10-kubeadm.conf
    if ${@bb.utils.contains('DISTRO_FEATURES','usrmerge','true','false',d)}; then
            install -d "${D}${BIN_PREFIX}${base_bindir}"
            install -m 755 "${WORKDIR}/k8s-init" "${D}${BIN_PREFIX}${base_bindir}"
            rm -rf ${D}/bin/
    fi
}

FILES:${PN}-host = "${BIN_PREFIX}${base_bindir}/k8s-init"
