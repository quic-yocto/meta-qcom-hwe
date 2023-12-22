include $(TOPDIR)/rules.mk

PKG_NAME:=sec-config
PKG_VERSION:=1.0
PKG_RELEASE:=1
PKG_FIXUP:=autoreconf

include $(INCLUDE_DIR)/package.mk

define Package/sec-config
  SECTION:=QTI
  CATEGORY:=QTI-PACKAGES
  TITLE:=Security Configuration
  DEPENDS:= +glib2 +qmi-framework
endef

define Package/sec-config/Description
	Security Configuration for QMI communication
endef

define Build/Prepare
	mkdir -p $(PKG_BUILD_DIR)/
	$(CP) ./files/* $(PKG_BUILD_DIR)/
endef

define Build/Configure
	@true
endef

define Build/Compile
	@true
endef

define Build/InstallDev
	$(INSTALL_DIR) $(STAGING_DIR)
	mkdir -p $(STAGING_DIR)/etc
	$(CP) $(PKG_BUILD_DIR)/sec_config $(STAGING_DIR)/etc
endef

define Package/sec-config/install
	$(INSTALL_DIR) $(1)/etc/
	$(INSTALL_DATA) $(PKG_BUILD_DIR)/sec_config $(1)/etc/sec_config
endef

$(eval $(call BuildPackage,sec-config))
