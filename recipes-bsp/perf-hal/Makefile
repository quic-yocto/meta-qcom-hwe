include $(TOPDIR)/rules.mk

PKG_NAME:=perf-hal
PKG_VERSION:=1.0
PKG_RELEASE:=1
PKG_FIXUP:=autoreconf
PKG_INSTALL:=1
BASEMACHINE=sdxpinn
SOURCE_DIR:= $(TOPDIR)/src/vendor/qcom/proprietary/perf-core
include $(INCLUDE_DIR)/package.mk

CONFIGURE_ARGS += --enable-openwrt-perf=yes
define Package/perf-hal
	SECTION:=QTI
	CATEGORY:=QTI-PACKAGES
	TITLE:=PERF HAL
	DEPENDS:=+glib2 +liblog +libcutils +libutils +libxml2
endef

define Package/perf-hal/description
	perf hal.
endef

define Build/Prepare
	mkdir -p $(PKG_BUILD_DIR)
	cp -rp $(SOURCE_DIR)/* $(PKG_BUILD_DIR)
endef


define Package/perf-hal/install
	$(INSTALL_DIR) $(1)/usr/lib
	$(INSTALL_DIR) $(1)/usr/bin
	$(INSTALL_DIR) $(1)/etc/perf/
	$(INSTALL_DIR) $(1)/lib/modules
	$(INSTALL_BIN) $(PKG_BUILD_DIR)/ipkg-install/usr/lib/*.so $(1)/usr/lib
	$(INSTALL_BIN) $(PKG_BUILD_DIR)/ipkg-install/usr/bin/* $(1)/usr/bin
	x=`ls -1 $(SOURCE_DIR)/configs/common/*.xml | wc -l`;\
	if [ -d "$(SOURCE_DIR)/configs/common" ] && [ "$$$$(ls -1 $(SOURCE_DIR)/configs/common/*.xml | wc -l)" -ne "0" ]; then\
	$(INSTALL_BIN) $(SOURCE_DIR)/configs/common/*.xml $(1)/etc/perf/;\
	fi
	if [[ -d "$(SOURCE_DIR)/configs/test" ]] && [ "$$$$(ls -1 $(SOURCE_DIR)/configs/test/*.xml | wc -l)" -ne "0" ]; then\
	$(INSTALL_BIN) $(SOURCE_DIR)/configs/test/*.xml $(1)/etc/perf/;\
	fi
	if [[ -d "$(SOURCE_DIR)/configs/test/common" ]] && [ "$$$$(ls -1 $(SOURCE_DIR)/configs/test/common/*.xml | wc -l)" -ne "0" ]; then\
	$(INSTALL_BIN) $(SOURCE_DIR)/configs/test/common/*.xml $(1)/etc/perf/;\
	fi
	if [[ -d "$(SOURCE_DIR)/configs/$(BASEMACHINE)" ]] && [ "$$$$(ls -1 $(SOURCE_DIR)/configs/$(BASEMACHINE) | wc -l)" -ne "0" ]; then \
	$(INSTALL_BIN) $(SOURCE_DIR)/configs/$(BASEMACHINE)/*.xml $(1)/etc/perf/;\
	fi
	if [[ -d "$(SOURCE_DIR)/configs/test/$(BASEMACHINE)" ]] && [ "$$$$(ls -1 $(SOURCE_DIR)/configs/test/$(BASEMACHINE) | wc -l)" -ne "0" ]; then\
	$(INSTALL_BIN) $(SOURCE_DIR)/configs/test/$(BASEMACHINE)/*.xml $(1)/etc/perf/;\
	fi
	if [ "$$$$(ls -1 $(1)/etc/perf/powerhint.xml | wc -l)" -ne "0" ]; then\
	mv $(1)/etc/perf/powerhint.xml $(1)/etc/powerhint.xml;\
	fi
	$(INSTALL_DIR) $(1)/data/vendor/perfd
	$(INSTALL_DIR) $(1)/etc/init.d
	$(INSTALL_BIN) ./perf-hal.init $(1)/etc/init.d/
endef

$(eval $(call BuildPackage,perf-hal))
