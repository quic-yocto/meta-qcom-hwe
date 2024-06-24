SUMMARY = "packagegroups for k8s"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

inherit packagegroup

PACKAGES = "packagegroup-qcom-k8s"

KUBERNETES_CRI ?= "containerd"

RDEPENDS:packagegroup-qcom-k8s = " \
    packagegroup-${KUBERNETES_CRI} \
    packagegroup-oci \
    kubernetes \
    kubeadm \
    kubernetes-misc \
"

RCONFLICTS:${PN} = "packagegroup-k8s-host packagegroup-k8s-node"
