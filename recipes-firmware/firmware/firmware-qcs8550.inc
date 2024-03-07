CHIPCODE_SRC_URI    = "qpm-git.qualcomm.com/home2/git/qualcomm/qualcomm-linux-spf-1-0_test_device_public"
CHIPCODE_SRC_REV    = "75f5ea137569f109f6b1a58236b0e1a055e07142"
CHIPCODE_SRC_BRANCH = "master"

BUILD_ID  = "QCM8550.LE.2.0"
BIN_PATH  = "common/build/ufs/bin"

# Fetch a shallow mirror tarball.
BB_GIT_SHALLOW       = "1"
# Fetch only the top commit
BB_GIT_SHALLOW_DEPTH = "1"
# Generate shallow mirror tarballs
BB_GENERATE_SHALLOW_TARBALLS = "1"
