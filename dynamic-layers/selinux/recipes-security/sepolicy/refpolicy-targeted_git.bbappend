POLICY_MONOLITHIC = "y"

export POLICY_MONOLITHIC

FILESEXTRAPATHS:append := "${THISDIR}:"
FILESEXTRAPATHS:append := "${THISDIR}/patches:"


QCOM_STORE_ROOT = "/etc/selinux/policy-store"

FILES:${PN} += " \
        ${sysconfdir}/selinux/${POLICY_NAME}/ \
        ${datadir}/selinux/${POLICY_NAME}/*.pp \
        ${QCOM_STORE_ROOT}/${POLICY_NAME}/ \
"
SRC_URI:remove:qcom = "file://0042-policy-modules-system-systemd-systemd-user-fixes.patch"

#Patches
SRC_URI:append:qcom = " file://0070-PENDING-allow-logging-domains-to-execute-busybox.patch \
            file://0071-PENDING-Add-net_admin-capability-to-modutils.patch \
            file://0072-PENDING-allow-systemd-generator-to-getattr-of-unit-files.patch \
            file://0073-PENDING-add-perms-to-mount-to-resolve-service-fails.patch \
            file://0074-PENDING-add-required-policies-for-functionfs.patch \
	    file://0075-PENDING-policies-for-serial-login.patch \
	    file://0076-PENDING-add-sepolicies-for-pulseaudio-audio-device.patch \
	    file://0077-PENDING-Add-sepolicy-for-systemd-failure-services.patch \
            file://0078-PENDING-add-sepolicies-for-modem-manager.patch \
            file://0079-PENDING-Add-sepolicy-for-systemd-networkd-wait-online.patch \
            file://0080-PENDING-Add-sepolicy-rules-for-hostapd-hostapd_cli.patch \
            file://0081-PENDING-SEPolicy-changes-to-allow-read-write-to-dbus-and-bl.patch \
            file://0082-PENDING-sepolicy-for-bluez-to-access-uhid.patch \
            file://0083-PENDING-Add-Docker-related-policies.patch \
            file://0084-PENDING-Allow-SE-policy-read-and-write-access-to-dbu.patch \
            file://0085-PENDING-Adding-rules-for-dnsmasq.patch \
            file://0086-PENDING-networkmanager-allow-access-tmpfs.patch \
"

#Policy folders
SRC_URI:append:qcom = " file://apps/ \
            file://kernel/ \
            file://roles/ \
            file://services/ \
            file://system/ \
            file://admin/ \
            file://target/ \
            file://files/ \
"

RDEPENDS:${PN} += " \
                   selinux-autorelabel \
"

#enable test sepolicy
ENABLE_TEST_SEPOLICY ?= "y"
SRC_URI:append:qcom = "\
            ${@bb.utils.contains('ENABLE_TEST_SEPOLICY', 'y', 'file://test/', '', d)} \
            file://0996-QCLINUX-file_contexts.subs_dist-set-aliases-for-var-lib-seli.patch \
            file://0997-QCLINIUX-sepolicy-update-file_contexts.subs_dist-for-support.patch \
            file://0998-refpolicy-config-update-ssh-to-login-in-sysadmin-rol.patch \
"

EXTRA_OEMAKE += "tc_usrsbindir=${STAGING_SBINDIR_NATIVE}"
EXTRA_OEMAKE += "tc_sbindir=${STAGING_DIR_NATIVE}${base_sbindir_native}"

do_compile:qcom() {
        if [ -f "${WORKDIR}/modules.conf" ] ; then
                cp -f ${WORKDIR}/modules.conf ${S}/policy/modules.conf
        fi
        # oe_runmake conf
        disable_policy_modules
        oe_runmake policy
}

prepare_policy_store () {
        oe_runmake 'DESTDIR=${D}' 'prefix=${D}${prefix}' install
        POL_PRIORITY=100
        POL_SRC=${D}${datadir}/selinux/${POLICY_NAME}
        POL_STORE=${D}${QCOM_STORE_ROOT}/${POLICY_NAME}
        POL_ACTIVE_MODS=${POL_STORE}/active/modules/${POL_PRIORITY}

        # Prepare to create policy store
        mkdir -p ${POL_STORE}
        mkdir -p ${POL_ACTIVE_MODS}

        # get hll type from suffix on base policy module
        HLL_TYPE=$(echo ${POL_SRC}/base.* | awk -F . '{if (NF>1) {print $NF}}')
        HLL_BIN=${STAGING_DIR_NATIVE}${prefix}/libexec/selinux/hll/${HLL_TYPE}
        if [ "$POLICY_MONOLITHIC" = "n" ]; then
        for i in ${POL_SRC}/*.${HLL_TYPE}; do
                MOD_NAME=$(basename $i | sed "s/\.${HLL_TYPE}$//")
                MOD_DIR=${POL_ACTIVE_MODS}/${MOD_NAME}
                mkdir -p ${MOD_DIR}
                echo -n "${HLL_TYPE}" > ${MOD_DIR}/lang_ext
                if ! bzip2 -t $i >/dev/null 2>&1; then
                        ${HLL_BIN} $i | bzip2 --stdout > ${MOD_DIR}/cil
                        bzip2 -f $i && mv -f $i.bz2 $i
                else
                        bunzip2 --stdout $i | \
                                ${HLL_BIN} | \
                                bzip2 --stdout > ${MOD_DIR}/cil
                fi
                cp $i ${MOD_DIR}/hll
        done
        fi
}


rebuild_policy () {
        cat <<-EOF > ${D}${sysconfdir}/selinux/semanage.conf
module-store = direct
[setfiles]
path = ${STAGING_DIR_NATIVE}${base_sbindir_native}/setfiles
args = -q -c \$@ \$<
[end]
[sefcontext_compile]
path = ${STAGING_DIR_NATIVE}${sbindir_native}/sefcontext_compile
args = \$@
[end]

policy-version = 33
store-root = "${QCOM_STORE_ROOT}"
EOF

        # Create policy store and build the policy
        semodule -p ${D} -s ${POLICY_NAME} -n -B
        rm -f ${D}${sysconfdir}/selinux/semanage.conf
        # no need to leave final dir created by semanage laying around
        rm -rf ${D}${QCOM_STORE_ROOT}/final
}

COMPATIBLE_MACHINE = "qcm6490|qcs9100|qcs8300"

def get_machine(d):
    need_machine = (d.getVar('COMPATIBLE_MACHINE') or "").split("|")
    if need_machine:
        import re
        compat_machines = (d.getVar('MACHINEOVERRIDES') or "").split(":")
        for n in need_machine:
            for m in compat_machines:
                if re.match(n, m):
                    return n

def test_modules_list(d):
    machine = get_machine(d)

    target_to_policy_map = {
        'qcm6490': ['qcm6490_test', 'qcs9100_test'],
        'qcs9100': ['qcm6490_test', 'qcs9100_test'],
        'qcs8300': ['qcm6490_test', 'qcs9100_test'],
        'qcm8550': ['qcm8550_test'],
        'qcs8550': ['qcs8550_test'],
    }

    if machine in target_to_policy_map:
        return target_to_policy_map[machine]
    else:
        return None

def target_modules_list(d):
    machine = get_machine(d)

    target_to_policy_map = {
        'qcm6490': ['qcm6490', 'qcs9100'],
        'qcs9100': ['qcm6490', 'qcs9100'],
        'qcs8300': ['qcm6490', 'qcs9100'],
        'qcm8550': ['qcm8550'],
        'qcs8550': ['qcs8550'],
    }

    if machine in target_to_policy_map:
        return target_to_policy_map[machine]
    else:
        return None

def copy_target_policies(src_path, dest_path, src_folder, dest_folder, d):
    import shutil
    import os

    if src_folder is 'test':
        policy_modules = ["common_test"]
        test_modules = test_modules_list(d)
        if test_modules:
            policy_modules += test_modules
    else:
        policy_modules = target_modules_list(d)

    if policy_modules is None:
        return

    for policy_module in policy_modules:
        source_files = [f"{policy_module}.{ext}" for ext in ['te', 'if', 'fc']]
        src_dir = os.path.join(src_path, src_folder)
        dest_dir = os.path.join(dest_path, dest_folder)
        for file in source_files:
            src_file = os.path.join(src_dir, file)
            dest_file = os.path.join(dest_dir, file)
            shutil.copyfile(src_file, dest_file)


def append_policy_file(src_path, dst_path):
    # Append our policy fragment to the end of the upstream file
    with open(src_path, 'r') as src_file:
        with open(dst_path, 'a') as dst_file:
            dst_file.write('\n')
            for line in src_file.readlines():
                dst_file.write(line)

def copy_policies(src_path, dst_path, dir_list, d):
    import shutil
    copy_target_policies(src_path, dst_path, "target", "kernel", d)

    if d.getVar("ENABLE_TEST_SEPOLICY") is 'y':
        copy_target_policies(src_path, dst_path, "test", "apps", d)

    for dir in dir_list:
       src_dir = os.path.join(src_path, dir)
       dst_dir = os.path.join(dst_path, dir)
       if os.path.exists(src_dir):
           files = os.listdir(src_dir)
           for file_name in files:
               qc_module_path = os.path.join(src_dir, file_name)
               ref_module_path = os.path.join(dst_dir, file_name)
               if os.path.exists(ref_module_path):
                   append_policy_file(qc_module_path, ref_module_path)
               else:
                   shutil.copy2(qc_module_path, ref_module_path)

def copy_conf(src_path, dst_path, d):
    import shutil
    conf_file_path = os.path.join(src_path, "modules.conf")
    if os.path.exists(src_path):
        shutil.copy2(conf_file_path, dst_path)
    conf_file_path = os.path.join(src_path, "booleans.conf")
    if os.path.exists(src_path):
        shutil.copy2(conf_file_path, dst_path)

def install_policy(d):
    src_path = os.path.join(d.getVar("WORKDIR"))
    dst_path = os.path.join(d.getVar("S"), "policy", "modules")
    dir_list = ["apps","kernel","roles","services","system","admin"]
    # copy policies
    copy_policies(src_path, dst_path, dir_list,d)
    # copy modules.conf
    conf_src_path = os.path.join(d.getVar("WORKDIR"), "files")
    conf_dst_path = os.path.join(d.getVar("S"), "policy")
    copy_conf(conf_src_path, conf_dst_path,d)

do_patch:append:qcom() {
    install_policy(d)
}
