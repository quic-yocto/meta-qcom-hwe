PACKAGECONFIG:append:qcom = " ${@bb.utils.filter('DISTRO_FEATURES', 'opengl', d)}"
