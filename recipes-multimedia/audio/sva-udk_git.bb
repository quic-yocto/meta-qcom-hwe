inherit qprebuilt pkgconfig

LICENSE          = "Qualcomm-Technologies-Inc.-Proprietary"
LIC_FILES_CHKSUM = "file://${QCOM_COMMON_LICENSE_DIR}${LICENSE};md5=58d50a3d36f27f1a1e6089308a49b403"

DESCRIPTION = "sva-uv"

DEPENDS += "sva-common sva-eai-utils sva-eai vui-interface-header"

PBT_ARCH = "armv8-2a"

SRC_URI[armv8-2a.sha256sum] = "9fcce9fea0b2a9af911f2f360bc0853366c1998654c8428a5d321232d06657a7"

SRC_URI = "https://${PBT_ARTIFACTORY}/${PBT_BUILD_ID}/${PBT_BIN_PATH}/${BPN}_${PV}_${PBT_ARCH}.tar.gz;name=${PBT_ARCH}"
