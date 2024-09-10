inherit qprebuilt pkgconfig systemd

LICENSE          = "Qualcomm-Technologies-Inc.-Proprietary"
LIC_FILES_CHKSUM = "file://${QCOM_COMMON_LICENSE_DIR}${LICENSE};md5=58d50a3d36f27f1a1e6089308a49b403"

DESCRIPTION = "QMI framework will provide sample applications to test QMI communication between Apps and other remote sub systems. It also includes multiple services to support the QMI communications. qrtr-ns service should be running in background to support QMI communication because qrtr-ns is the one which handles the qrtr control packet. qrtr-filter service is required if the target want QMI access control which avoids unprivileged access to QMI services"

DEPENDS += "glib-2.0 property-vault syslog-plumber qrtr"

SRC_URI[qcm6490.sha256sum] = "53f55a4e2356cf9be8e869084d10370634c03d1b82840e6fac1c019ffb626b64"
SRC_URI[qcs9100.sha256sum] = "5b364e6f79108e291ca993ea58fb5b695607bd5fc6a0f348c8ba8a61626d4f5f"
SRC_URI[qcs8300.sha256sum] = "6b8e66e3db9f8177a9b82ca337aeb55bbbc4fc10fa40b38c4414b5d54b7e6254"

SRC_URI = "https://${PBT_ARTIFACTORY}/${PBT_BUILD_ID}/${PBT_BIN_PATH}/${BPN}_${PV}_${PBT_ARCH}.tar.gz;name=${PBT_ARCH}"

FILES:${PN} += "${systemd_unitdir}/system/"

