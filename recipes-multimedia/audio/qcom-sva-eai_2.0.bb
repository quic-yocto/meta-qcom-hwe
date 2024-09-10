inherit qprebuilt pkgconfig

LICENSE          = "Qualcomm-Technologies-Inc.-Proprietary"
LIC_FILES_CHKSUM = "file://${QCOM_COMMON_LICENSE_DIR}${LICENSE};md5=58d50a3d36f27f1a1e6089308a49b403"

DESCRIPTION = "sva-eai"

DEPENDS += "qcom-sva-ml-commondwarf2-3 qcom-sva-common"

PBT_ARCH = "armv8-2a"

SRC_URI[armv8-2a.sha256sum] = "098cff4ecbb819de80aead444860902bb28e2181c3a945f591cf75c4243169b0"

SRC_URI = "https://${PBT_ARTIFACTORY}/${PBT_BUILD_ID}/${PBT_BIN_PATH}/${BPN}_${PV}_${PBT_ARCH}.tar.gz;name=${PBT_ARCH}"

SOLIBS = ".so"
FILES_SOLIBSDEV = ""
