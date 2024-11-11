# Install NHLOS boot binaries in DEPLOY_DIR

inherit deploy

do_deploy() {
    find "${UNPACKDIR}/${BOOTBINARIES}" -name '*.bin' -exec install -m 0644 {} ${DEPLOYDIR} \;
    find "${UNPACKDIR}/${BOOTBINARIES}" -name '*.elf' -exec install -m 0644 {} ${DEPLOYDIR} \;
    find "${UNPACKDIR}/${BOOTBINARIES}" -name '*.fv' -exec install -m 0644 {} ${DEPLOYDIR} \;
    find "${UNPACKDIR}/${BOOTBINARIES}" -name '*.mbn' -exec install -m 0644 {} ${DEPLOYDIR} \;
    find "${UNPACKDIR}/${BOOTBINARIES}" -name '*.melf' -exec install -m 0644 {} ${DEPLOYDIR} \;
}
addtask deploy before do_build after do_install
