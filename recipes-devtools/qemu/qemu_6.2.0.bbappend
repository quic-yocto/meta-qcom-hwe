PACKAGECONFIG:append:qcom = " virtfs vhost gtk+ libusb ftrace"
PACKAGECONFIG[ftrace] = "--enable-trace-backend=ftrace,,,"
PACKAGECONFIG[ust] = "--enable-trace-backends=ust,,lttng-ust,"
FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:"

SRC_URI += "file://0001-softmmu-fix-device-deletion-events-with-device-JSON-.patch"

