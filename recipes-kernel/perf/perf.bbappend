PACKAGECONFIG:qcom = "tui libunwind"

PACKAGECONFIG[scripting] = ",NO_LIBPERL=1 NO_LIBPYTHON=1 NO_JEVENTS=1,perl python3 python3-setuptools-native"
PACKAGECONFIG[traceevent] = ",NO_LIBTRACEEVENT=1"
