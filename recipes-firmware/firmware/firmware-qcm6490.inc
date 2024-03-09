CHIPCODE_SRC_URI    = "qpm-git.qualcomm.com/home2/git/qualcomm/qualcomm-linux-spf-1-0_test_device_public"
CHIPCODE_SRC_REV    = "b24e1bc1ba701d1452e0fddf3ac15b57eda5c2ad"
CHIPCODE_SRC_BRANCH = "master"

BUILD_ID  = "QCM6490.LE.1.0"
BIN_PATH  = "common/build/ufs/bin"

# Fetch a shallow mirror tarball.
BB_GIT_SHALLOW       = "1"
# Fetch only the top commit
BB_GIT_SHALLOW_DEPTH = "1"
# Generate shallow mirror tarballs
BB_GENERATE_SHALLOW_TARBALLS = "1"
