# The upstream repository has switched to a main branch, we update our recipe to match.
SRC_URI = "git://${PKG_NAME};destsuffix=git/src/${PKG_NAME};branch=main;protocol=https"
