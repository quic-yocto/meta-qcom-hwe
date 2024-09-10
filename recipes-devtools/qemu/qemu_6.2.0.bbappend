PACKAGECONFIG:append:qcom = " virtfs vhost gtk+ libusb ftrace"
PACKAGECONFIG[ftrace] = "--enable-trace-backend=ftrace,,,"
PACKAGECONFIG[ust] = "--enable-trace-backends=ust,,lttng-ust,"
