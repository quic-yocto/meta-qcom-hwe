inherit autotools python3native deploy

LICENSE          = "Qualcomm-Technologies-Inc.-Proprietary"
LIC_FILES_CHKSUM = "file://inc/verify.h;beginline=2;endline=4;md5=4ecb45f64c173d2c611ae516932f51b5"

SUMMARY = "dspservices ta"

#securemsm-features is not available on trustedvm
DEPENDS:qcm6490 = "fastrpc securemsm-headers minkipc libvmmem securemsm-features"
DEPENDS:trustedvm-v3 += "securemsm-noship python3-grpcio-tools-native qtvm-sdk"

SRC_URI   = "git://qpm-git.qualcomm.com/home2/git/revision-history/platform/vendor/qcom-proprietary/dspservices_ship.git;protocol=https;rev=658578dcc7915bc1a33dddbc8dbe29420f3a88bd;branch=${CUST_ID}-dsp-services-vndr.lnx.15.0.r1-rel;destsuffix=dspservices_ship"

SECTOOLS_PATH = "${WORKSPACE}/vendor/qcom/proprietary/sectools/Linux"
SECURITY_PROFILES_PATH = "${WORKSPACE}/security/securemsm/security_profiles"
TARGET_SECURITY_PROFILE = "${VM_TARGET}_tz_security_profile.xml"

S = "${WORKDIR}/dspservices_ship"

# build and copy all libraries and binaries as part of rootfs based on machine , distro or combined features.
PACKAGECONFIG ??= " \
			${@bb.utils.contains_any('MACHINE', 'qcm6490', 'loadalgo', '', d)} \
			${@bb.utils.contains_any('MACHINE', 'qcm6490', 'syslog', '', d)} \			
"

PACKAGECONFIG[ION] = "--with-ion, --without-ion"
PACKAGECONFIG[syslog] = "--with-syslog, --without-syslog"
PACKAGECONFIG[loadalgo] = "--enable-loadalgo,--disable-loadalgo"

EXTRA_OEMAKE += "MACHINE='${MACHINE}'"

do_install () {
   if ${@bb.utils.contains('MACHINE', 'trustedvm-v3', 'true','false', d)}; then
      python3 ${STAGING_DIR_TARGET}${libdir}/sdk/ext/Build.py --i ${WORKDIR}/dspservices_ship/loadalgo_ta_tvm/build.json || (echo "Loadalgo_TA build failed $$?"; exit 1);
   fi
}

do_deploy () {
   if ${@bb.utils.contains('MACHINE', 'trustedvm-v3', 'true','false', d)}; then
      ${SECTOOLS_PATH}/sectools secure-image \
      ${WORKDIR}/dspservices_ship/loadalgo_ta_tvm/bin/Loadalgo_TA \
      --outfile ${WORKDIR}/dspservices_ship/loadalgo_ta_tvm/bin/Loadalgo_TA \
      --security-profile ${SECURITY_PROFILES_PATH}/${TARGET_SECURITY_PROFILE} \
      --image-id TZ-APP-OEM \
      --sign \
      --platform-binding INDEPENDENT \
      --signing-mode TEST
      mkdir -p ${DEPLOYDIR}/trustedvm/
      install -m 0755 ${WORKDIR}/dspservices_ship/loadalgo_ta_tvm/bin/Loadalgo_TA ${DEPLOYDIR}/trustedvm/Loadalgo_TA
   fi
}

addtask do_deploy after do_install before do_package

FILES:${PN} = "${libdir}/*.so ${bindir}/*"
FILES:${PN}-dev = "${libdir}/*.la ${includedir}"

PACKAGE_ARCH    ?= "${MACHINE_ARCH}"
