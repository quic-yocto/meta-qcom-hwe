inherit cargo systemd pkgconfig

SUMMARY = "CrosVM support"
HOMEPAGE = "https://chromium.googlesource.com/chromiumos/platform/crosvm/"
LICENSE = "BSD-3-Clause-Clear"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/BSD-3-Clause-Clear;md5=7a434440b651f4a472ca93716d01033a"

DEPENDS += "cargo-native libcap rust-native rust-llvm-native wayland wayland-native protobuf-native wayland-protocols"

SRCREV = "5694c8c2ce6a5d5ec22dbe14d9c63b7adc624187"
SRC_URI = "git://chromium.googlesource.com/crosvm/crosvm.git;branch=main;protocol=https"
SRC_URI += "file://0001-Cargo.toml-do-not-abort-on-panic.patch"

#without .patch suffix, because we need to manually apply this patch to a git submodule 
SRC_URI += "file://0002-minijail-sys-build.rs-set-sysroot-path"

CARGO_DISABLE_BITBAKE_VENDORING = "1"

do_configure[network] = "1"
do_compile[network] = "1"

S = "${WORKDIR}/git"

CARGO_BUILD_FLAGS += "--features=gunyah"

do_configure:prepend() {
    cd ${WORKDIR}/git
    git submodule update --init --recursive
    cd ${WORKDIR}/git/third_party/minijail
    sed -i 's%#TO_BE_REPLACED#%${STAGING_DIR_TARGET}%' ${WORKDIR}/0002-minijail-sys-build.rs-set-sysroot-path
    git apply ${WORKDIR}/0002-minijail-sys-build.rs-set-sysroot-path
}
