POLICY_MONOLITHIC = "y"

FILESEXTRAPATHS:append := "${THISDIR}:"

SRC_URI:remove = "file://0042-policy-modules-system-systemd-systemd-user-fixes.patch"

SRC_URI += "file://apps/ \
            file://kernel/ \
	    file://roles/ \
	    file://services/ \
	    file://system/ \
            file://admin/ \
"

EXTRA_OEMAKE += "tc_usrsbindir=${STAGING_SBINDIR_NATIVE}"
EXTRA_OEMAKE += "tc_sbindir=${STAGING_DIR_NATIVE}${base_sbindir_native}"

prepare_policy_store () {
        oe_runmake 'DESTDIR=${D}' 'prefix=${D}${prefix}' install
        POL_PRIORITY=100
        POL_SRC=${D}${datadir}/selinux/${POLICY_NAME}
        POL_STORE=${D}${localstatedir}/lib/selinux/${POLICY_NAME}
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

def append_policy_file(src_path, dst_path):
    # Append our policy fragment to the end of the upstream file
    with open(src_path, 'r') as src_file:
        with open(dst_path, 'a') as dst_file:
            dst_file.write('\n')
            for line in src_file.readlines():
                if not line.startswith('#'):
                    dst_file.write(line)

def copy_policies(src_path, dst_path, dir_list):
    import shutil
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

def install_policy(d):
    src_path = os.path.join(d.getVar("WORKDIR"))
    dst_path = os.path.join(d.getVar("S"), "policy", "modules")
    dir_list = ["apps","kernel","roles","services","system","admin"]
    # copy policies
    copy_policies(src_path, dst_path, dir_list)

do_patch:append() {
    install_policy(d)
}